package tests;

import model.AddressData;
import org.junit.jupiter.api.Test;


public class AddressRemovalTests extends TestBase1{

    @Test
    public void canRemoveAddress() {
        openHomePage();
        if (!isAddressPresent()) {
            createAddress(new AddressData("Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }
        removeAddress();
    }

}
