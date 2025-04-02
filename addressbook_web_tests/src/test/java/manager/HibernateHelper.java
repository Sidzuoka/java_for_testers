package manager;

import manager.hbm.GroupRecord;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.schema.Action;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase{
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        //создание фабрики сессий - подключение к БД


        sessionFactory = new Configuration()
                        //.addAnnotatedClass(Book.class)
                        .addAnnotatedClass(GroupRecord.class) //работать с классом, где опис. привязка к табл. БД
                        // MySQL
                        .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost/addressbook")
                        // Credentials
                        .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root")
                        .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
                        // Create a new SessionFactory
                        .buildSessionFactory();

    }

    //специальная ф-ия, кот. из объектов GroupData, строит объекты GroupRecord
    static List<GroupData> convertList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        //переменная пробегает по списку GroupRecord и для каждого из них добавляем новый объект GroupData, кот-ый строится из GroupRecord
        for (var record : records) {
            result.add(convert(record));

        }
        return result;
    }


    //конвертирует не только списки, но и отдельные объекты тоже
    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
        //приводим к строке тип данных число для id, полученного из таблицы, через конкатенацию со строкой
    }

    //из о. типа GroupData строит о. GroupRecord типа
    private static GroupRecord convert(GroupData data) {
        //чтобы не сломался parseInt(id) - т.к. при создании нового объекта id нет - пустая строка
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        //вызывает конструктор и передает туда параметры /из строки в число - id
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
        //приводим к строке тип данных число для id, полученного из таблицы, через конкатенацию со строкой
    }


    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list(); //нужны данные типа GroupData - в List так заявлено - используем ф-ию - преобразователь типов из GroupRecord в GroupData
        }));
    }


    //считает кол-во групп в результате выполнения запроса
    public long getGroupCount() {
        return (sessionFactory.fromSession(session -> {
            //.fromSession - когда нужно что-то вернуть
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult(); //нужны данные типа GroupData - в List так заявлено - используем ф-ию - преобразователь типов из GroupRecord в GroupData
        }));
    }

    //выполнить запрос к БД, который создает новый объект
    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            //нужно сохранять объект типа Record, т.е. нужно выполнить обратную конвертацию из GroupData -> GroupRecord
            session.persist(convert(groupData));
            session.getTransaction().commit(); //открытые сессии сохраняем в консистентном состоянии
        });
        //.inSession - выполнить действие, ничего не возвращать


    }
}
