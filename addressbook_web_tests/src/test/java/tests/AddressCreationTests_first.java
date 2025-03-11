/*package tests;

import org.junit.jupiter.api.Test;

public class AddressCreationTests_first extends TestBase{

    @Test
    public void canCreateAddress() {
        openAddressPage();
        createAddress(new AddressData("Lev", "Nikolaevich", "Tolstoj", "lev", "War", "Peace", "lev@yandex.ru", "lev@mail.ru", "lev@google.com", "www.lev.ru"));
    }

    @Test
    public void canCreateAddressWithEmptyField(){
        openAddressPage();
        createAddress(new AddressData());
    }

    @Test
    public void canCreateAddressWithFirstNameOnly(){
        openAddressPage();
        createAddress(new AddressData().withFirstName("NewLev"));
    }


}
*/