package tests;

import model.AddressData;
import org.junit.jupiter.api.Test;


public class AddressRemovalTests extends TestBase{

    @Test
    public void canRemoveAddress() {
        app.address().openHomePage();
        if (!app.address().isAddressPresent()) {
            app.address().createAddress(new AddressData("Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }
        app.address().removeAddress();
    }

}
