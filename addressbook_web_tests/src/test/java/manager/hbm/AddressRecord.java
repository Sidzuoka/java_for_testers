package manager.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "addressbook")
public class AddressRecord {

    @Id
    public int id;

    public String lastname;
    public String firstname;
    public String address;
    public String home;
    public String email;
    //public String email2;
    //public String email3;
    //public String mobile;
    //public String work;
    public String phone2;

    public String middlename = "middlename";
    public String nickname = "nickname";
    public String company = "company";
    public String title = "title";
    public String mobile = "mobile";
    public String work = "work";
    public String fax = "fax";
    public String email2 = "email2";
    public String email3 = "email3";
    public String homepage = "homepage";



    public AddressRecord() {
    }

    public AddressRecord(int id, String lastname, String firstname, String address, String home, String email) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.home = home;
        this.email = email;

    }

}
