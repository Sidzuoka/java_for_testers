package tests;

import org.junit.jupiter.api.Test;


public class AddressRemovalTests extends TestBase1{

    @Test
    public void canRemoveAddress() {
        openHomePage();
        if (!isAddressPresent()) {
            createAddress("Firstname", "Middlename", "Lastname", "Nickname", "Title", "Company", "Address",
                    "Home", "Mobile", "Work", "Fax", "email", "email2", "email3", "homepage");
        }
        removeAddress();
    }

}
