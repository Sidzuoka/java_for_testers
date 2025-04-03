package manager;

import model.AddressData;
import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase{
    public JdbcHelper(ApplicationManager manager) { //чтобы мог получить доступ к mng-у, когда mng будет его инициализировать, передаст ссылку на самого себя и эта ссылка будет сохранена внутри помощника
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list"))
        {
            //построчное прочтение вернувшихся результатов из БД
            while (result.next()) {
                //формируем объект и добавляем его в список
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); //выбрасывается более общее, ловится более частное исключение
        }
        return groups;
    }

    public List<AddressData> getAddressList() {
        var address = new ArrayList<AddressData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT id, lastname, firstname, address, home, email FROM addressbook"))
        {
            while(result.next()) {
                address.add(new AddressData()
                        .withId(result.getString("id"))
                        .withLastName(result.getString("lastname"))
                        .withFirstName(result.getString("firstname"))
                        .withAddress(result.getString("address"))
                        .withHome(result.getString("home"))
                        .withEmail(result.getString("email"))
                        .withPhoto("src/test/resources/images/man.png"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }
}
