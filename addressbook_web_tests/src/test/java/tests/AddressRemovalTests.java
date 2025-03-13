package tests;

import model.AddressData;
import org.junit.jupiter.api.Test;


public class AddressRemovalTests extends TestBase1{

    @Test
    public void canRemoveAddress() {
        appmng.openHomePage();
        if (!appmng.isAddressPresent()) {
            appmng.createAddress(new AddressData("Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }
        appmng.removeAddress();
    }

}
