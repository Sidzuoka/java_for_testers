package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;

public class AddressCreationTests extends TestBase{


    public static List<AddressData> addressProvider() {
        var result = new ArrayList<AddressData>();
        for (var firstName : List.of("", "firstName")) {
            for (var lastName : List.of("", "lastName")) {
                for (var address : List.of("", "address")) {
                    for (var home : List.of("", "home")) {
                        for (var email: List.of("", "email")){
                            result.add(new AddressData(firstName, lastName, address, home, email));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new AddressData(randomString(i * 10), randomString(i * 10),
                    randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
    }


    public static List<AddressData> negativeAddressProvider() {
        var result = new ArrayList<AddressData>(List.of(
                new AddressData("FirstName'", "", "", "", "")));
        return result;
    }


    @ParameterizedTest
    @MethodSource("addressProvider")
    public void canCreateMultipleAddress(AddressData address) {
        int addressCount = app.address().getCountAddress();
        app.address().createAddress(address);
        int newAddressCount = app.address().getCountAddress();
        Assertions.assertEquals(addressCount + 1, newAddressCount);
    }

    @ParameterizedTest
    @MethodSource("negativeAddressProvider")
    public void canNotCreateAddress(AddressData address) {
        int addressCount = app.address().getCountAddress();
        app.address().createAddress(address);
        int newAddressCount = app.address().getCountAddress();
        Assertions.assertEquals(addressCount, newAddressCount);
    }

}
