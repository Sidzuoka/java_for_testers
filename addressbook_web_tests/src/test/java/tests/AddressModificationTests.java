package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class AddressModificationTests extends TestBase {


    @Test
    void canModifyAddress() {
        app.address().openHomePage();
        if (app.address().getCountAddress() == 0) {
            app.address().createAddress(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }
        var oldAddress = app.address().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());
        var testData = new AddressData().withLastName("modified LastName"); //FirstName
        //какой адр. модифицируем, какие данные в адр. модифицирцем
        app.address().modifyAddress(oldAddress.get(index), testData);
        var newAddress = app.address().getList();
        var expectedList = new ArrayList<>(oldAddress);
        expectedList.set(index, testData.withId(oldAddress.get(index).id()));
        Comparator<AddressData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAddress.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newAddress, expectedList);

    }
}
