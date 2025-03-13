package tests;

import model.AddressData;
import org.junit.jupiter.api.Test;


public class AddressRemovalTests extends TestBase1{

    @Test
    public void canRemoveAddress() {
        if (!appmng.address().isAddressPresent()) {
            appmng.address().createAddress(new AddressData("Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }
        appmng.address().removeAddress();
    }

}
