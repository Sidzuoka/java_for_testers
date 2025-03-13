package tests;

import model.AddressData;
import org.junit.jupiter.api.Test;

public class AddressCreationTests extends TestBase1{


    @Test
    public void canCreateAddress() {
        openAddressPage();
        createAddress( new AddressData("Firstname", "Lastname",
                "Address", "HomeTelephone", "email"));
    }

    @Test
    public void canCreateAddressWithEmptyField() {
        openAddressPage();
        createAddress(new AddressData());
    }

    @Test
    public void canCreateAddressWithFirstNameOnly() {
        openAddressPage();
        createAddress(new AddressData().withFirstName("OneField - FirstName"));
    }

}
