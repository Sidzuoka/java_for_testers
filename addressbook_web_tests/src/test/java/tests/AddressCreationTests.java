package tests;

import org.junit.jupiter.api.Test;

public class AddressCreationTests extends TestBase1{


    @Test
    public void canCreateAddress() {
        openAddressPage();
        createAddress("Firstname", "Middlename", "Lastname", "Nickname", "Title", "Company", "Address",
                "Home", "Mobile", "Work", "Fax", "email", "email2", "email3", "homepage");
    }

    @Test
    public void canCreateAddressWithEmptyField() {
        openAddressPage();
        createAddress("", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "");
    }

}
