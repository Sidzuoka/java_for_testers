package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AddressRemovalTests extends TestBase{

    @Test
    public void canRemoveAddress() {
        app.address().openHomePage();
        if (app.address().getCountAddress() == 0) {
            app.address().createAddress(new AddressData("Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }
        int addressCount = app.address().getCountAddress();
        app.address().removeAddress();
        int newAddressCount = app.address().getCountAddress();
        //Assertions.assertEquals(addressCount - 1, newAddressCount);
    }

}
