package ru.stqa.mantis.manager;

import jakarta.mail.*;
import ru.stqa.mantis.model.MailMessage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MailHelper extends HelperBase {

    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String username, String password) {
        try {
            //создаем сессию для работы с почтовым сервером
            var session = Session.getInstance(new Properties()); //пеердали пустой объект properties
            //получаем доступ к хранилищу почты - устанавливаем соединение с сервером почтовым
            Store store = session.getStore("pop3");
            store.connect("localhost",username, password);
            //открываем почтовый ящик
            var inbox = store.getFolder("INBOX"); //почтовый ящик один, называется INBOX
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
            store.close();
            return result;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
