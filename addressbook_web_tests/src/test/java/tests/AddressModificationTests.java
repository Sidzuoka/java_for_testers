package tests;

import model.AddressData;
import org.junit.jupiter.api.Test;

public class AddressModificationTests extends TestBase {


    @Test
    void canModifyAddress() {
        app.address().openHomePage();
        if (app.address().getCountAddress() == 0) {
            app.address().createAddress(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }
        app.address().modifyAddress(new AddressData().withFirstName("modified FirstName"));
    }
}
