package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressCreationTests extends TestBase{


    @Test
    public void canCreateAddress() {
        int addressCount = app.address().getCountAddress();
        app.address().createAddress( new AddressData("AFirstname", "ALastname",
                "Address", "HomeTelephone", "email"));
        int newAddressCount = app.address().getCountAddress();
        Assertions.assertEquals(addressCount + 1, newAddressCount);
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
