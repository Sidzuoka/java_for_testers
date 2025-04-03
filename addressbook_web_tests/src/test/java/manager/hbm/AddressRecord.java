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
    public String photo;

}
