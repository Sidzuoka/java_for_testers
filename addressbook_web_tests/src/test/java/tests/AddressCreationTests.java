package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AddressCreationTests extends TestBase{


    public static List<AddressData> addressProvider() {
        var result = new ArrayList<AddressData>();
        for (var firstname : List.of("", "firstname")) {
            for (var lastname : List.of("", "lastname")) {
                for (var address : List.of("", "address")) {
                    for (var home : List.of("", "home")) {
                        for (var email: List.of("", "email")){
                            result.add(new AddressData()
                                    .withFirstName(firstname)
                                    .withLastName(lastname)
                                    .withAddress(address)
                                    .withHome(home)
                                    .withEmail(email));

                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new AddressData()
                    .withFirstName(randomString(i * 10))
                    .withLastName(randomString(i * 10))
                    .withAddress(randomString(i * 10))
                    .withHome(randomString(i * 10))
                    .withEmail(randomString(i * 10)));
        }
        return result;
    }


    public static List<AddressData> negativeAddressProvider() {
        var result = new ArrayList<AddressData>(List.of(
                new AddressData("", "FirstName'", "", "", "", "")));
        return result;
    }


    @ParameterizedTest
    @MethodSource("addressProvider")
    public void canCreateMultipleAddress(AddressData address) {
        var oldAddress = app.address().getList();
        app.address().createAddress(address);
        var newAddress = app.address().getList();
        Comparator<AddressData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAddress.sort(compareById);
        var expectedList = new ArrayList<>(oldAddress);
        expectedList.add(address.withId(newAddress.get(newAddress.size() - 1).id())
                .withFirstName("")
                .withAddress("")
                .withHome("")
                .withEmail("")); //нашли элемент и взяли у него идентификатор
        expectedList.sort(compareById);
        Assertions.assertEquals(newAddress, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeAddressProvider")
    public void canNotCreateAddress(AddressData address) {
        var oldAddress = app.address().getList();
        app.address().createAddress(address);
        var newAddress = app.address().getList();
        Assertions.assertEquals(newAddress, oldAddress);
    }

}
