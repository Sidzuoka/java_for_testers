package manager;

import manager.hbm.AddressRecord;
import manager.hbm.GroupRecord;
import model.AddressData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase{
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        //создание фабрики сессий - подключение к БД


        sessionFactory = new Configuration()
                        .addAnnotatedClass(AddressRecord.class)
                        .addAnnotatedClass(GroupRecord.class) //работать с классом, где опис. привязка к табл. БД
                        // MySQL
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")//нулевые даты будут игнорироваться
                        // Credentials
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        // Create a new SessionFactory
                        .buildSessionFactory();

    }

    //специальная ф-ия, кот. из объектов GroupData, строит объекты GroupRecord
    static List<GroupData> converGrouptList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
        //переменная пробегает по списку GroupRecord и для каждого из них добавляем новый объект GroupData, кот-ый строится из GroupRecord
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
        return converGrouptList((List<GroupRecord>) sessionFactory.fromSession(session -> {
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



//--------------------------------------------------Address----------------------------------------------------------------------------------------------------------

    static List<AddressData> convertAddressList(List<AddressRecord> records) {
        return records.stream().map(HibernateHelper::convertToRecord).collect(Collectors.toList());
    }

    private static AddressData convertToRecord(AddressRecord record) {
        return new AddressData("" + record.id, record.firstname, record.lastname, record.address, record.home, record.email);
    }

    private static AddressRecord convertToAddrrData(AddressData address) {
        var id = address.id();
        if ("".equals(id)) {
            id  = "0";
        }
        return new AddressRecord(Integer.parseInt(id), address.firstname(), address.lastname(), address.address(), address.home(), address.email());
    }

    public List<AddressData> getAddressList() {
        return sessionFactory.fromSession(session -> {
            return convertAddressList((List<AddressRecord>) session.createQuery("from AddressRecord", AddressRecord.class).list());
        });
    }


    public long getAddressCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from AddressRecord", Long.class).getSingleResult();
        });
    }


    public void createAddress(AddressData addressData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertToAddrrData(addressData));
            session.getTransaction().commit();
        });
    }

    public List<AddressData> getAddresssInGroup(GroupData group) {
        return (sessionFactory.fromSession(session -> {
            return convertAddressList(session.get(GroupRecord.class, group.id()).addresses);
        }));
    }
}
