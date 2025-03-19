package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.List;

public class AddressCreationTests extends TestBase{


    public static List<String> addressFirstNameProvider() {
        var result = new ArrayList<String>();

        for (int i = 0; i < 5; i++) {
            result.add(randomString(i * 10));
        }
        return result;
    }

    @ParameterizedTest
    @ValueSource(strings = {"FirstName", "FirstName'"})
    public void canCreateAddress(String firstName) {
        int addressCount = app.address().getCountAddress();
        app.address().createAddress( new AddressData(firstName, "Lastname",
                "Address", "HomeTelephone", "email"));
        int newAddressCount = app.address().getCountAddress();
        Assertions.assertEquals(addressCount + 1, newAddressCount);
    }

    @Test
    public void canCreateAddressWithEmptyField() {
        app.address().createAddress(new AddressData());
    }

    @Test
    public void canCreateAddressWithFirstNameOnly() {
        app.address().createAddress(new AddressData().withFirstName("OneField - FirstName"));
    }

    @ParameterizedTest
    @MethodSource("addressFirstNameProvider")
    public void canCreateMultipleAddress(String firstName) {

        int addressCount = app.address().getCountAddress();

        app.address().createAddress(new AddressData(firstName, "Lastname",
                    "Address", "HomeTelephone", "email"));


        int newAddressCount = app.address().getCountAddress();
        Assertions.assertEquals(addressCount + n, newAddressCount);
    }

}
