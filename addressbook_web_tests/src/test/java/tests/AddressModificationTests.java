package tests;

import model.AddressData;
import org.junit.jupiter.api.Test;

public class AddressModificationTests extends TestBase1 {


    @Test
    void canModifyAddress() {
        appmng.address().openHomePage();
        if (!appmng.address().isAddressPresent()) {
            appmng.address().createAddress(new AddressData("Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }
        appmng.address().modifyAddress(new AddressData().withFirstName("modified FirstName"));
    }
}
