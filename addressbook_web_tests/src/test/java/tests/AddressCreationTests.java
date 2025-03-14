package tests;

import model.AddressData;
import org.junit.jupiter.api.Test;

public class AddressCreationTests extends TestBase{


    @Test
    public void canCreateAddress() {
        app.address().createAddress( new AddressData("AFirstname", "ALastname",
                "Address", "HomeTelephone", "email"));
    }

    @Test
    public void canCreateAddressWithEmptyField() {
        app.address().createAddress(new AddressData());
    }

    @Test
    public void canCreateAddressWithFirstNameOnly() {
        app.address().createAddress(new AddressData().withFirstName("OneField - FirstName"));
    }

}
