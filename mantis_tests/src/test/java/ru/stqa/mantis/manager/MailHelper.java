package ru.stqa.mantis.manager;

import jakarta.mail.*;
import ru.stqa.mantis.model.MailMessage;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MailHelper extends HelperBase {

    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String username, String password, Duration duration) {
        //ждем проверяем почту, ждем, проверяем почту, совсем ничего нет выдаем исключение
        var start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration.toMillis()) {
            try {
                var inbox = getInbox(username, password);
                //открываем ящик
                inbox.open(Folder.READ_ONLY); //только читаем почту, удалять ничего не будим (READ_WRITE)
                var messages = inbox.getMessages(); //возвращается массив, его нужно преобразовать в список модельных объектов
                //массив преобразовываем в модельные объекты
                var result =  Arrays.stream(messages)
                        .map(m -> {
                            try {
                                return new MailMessage()
                                        .withFrom(m.getFrom()[0].toString())
                                        .withContent((String) m.getContent()); //(String) m.getContent() - приводим к типу String
                            } catch (MessagingException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList();
                inbox.close();
                inbox.getStore().close(); //взяли переменную store из хранилища - избавились от нее, чтобы вынести метод с этой переменной
                if (result.size() > 0) {
                    return result;
                } //если список пустой, ничего не возвращаем и уходим на следующую итерацию цикла
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(1000); // 1 сек.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("No mail");
    }

    private static Folder getInbox(String username, String password) {
        //создаем сессию для работы с почтовым сервером
        var session = Session.getInstance(new Properties()); //пеердали пустой объект properties
        try {
            //получаем доступ к хранилищу почты - устанавливаем соединение с сервером почтовым
            Store store = session.getStore("pop3");
            store.connect("localhost", username, password);
            //открываем почтовый ящик
            var inbox = store.getFolder("INBOX"); //почтовый ящик один, называется INBOX
            return inbox;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


    public void drain(String username, String password) {
        try {
            //получили inbox
            var inbox = getInbox(username, password);
            //открываем inbox
            inbox.open(Folder.READ_WRITE);
            //массив
            Arrays.stream(inbox.getMessages()).forEach(m -> {
                try {
                    m.setFlag(Flags.Flag.DELETED, true);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });//setFlag - помечаем каждое письмо, как удаленное
            //при закрытии inbox, все письма, помеченные как DELETED, будут удалены
            inbox.close();
            //закрываем хранилище
            inbox.getStore().close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


}
