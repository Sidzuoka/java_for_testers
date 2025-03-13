package tests;

import model.AddressData;
import org.junit.jupiter.api.Test;

public class AddressCreationTests extends TestBase1{


    @Test
    public void canCreateAddress() {
        appmng.address().createAddress( new AddressData("Firstname", "Lastname",
                "Address", "HomeTelephone", "email"));
    }

    @Test
    public void canCreateAddressWithEmptyField() {
        appmng.address().createAddress(new AddressData());
    }

    @Test
    public void canCreateAddressWithFirstNameOnly() {
        appmng.address().createAddress(new AddressData().withFirstName("OneField - FirstName"));
    }

}
