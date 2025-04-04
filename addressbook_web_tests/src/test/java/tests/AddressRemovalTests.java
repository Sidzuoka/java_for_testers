package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class AddressRemovalTests extends TestBase{


    @Test
    public void canRemoveAddress() {
        if (app.hbm().getAddressCount() == 0) {
            app.hbm().createAddress(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }
        System.out.println(app.hbm().getAddressCount());
        var oldAddress = app.hbm().getAddressList();
        System.out.println(oldAddress);
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());
        app.address().removeAddress(oldAddress.get(index));
        var newAddress = app.hbm().getAddressList();
        var expectedList = new ArrayList<>(oldAddress);
        expectedList.remove(index);
        Assertions.assertEquals(newAddress, expectedList);
    }


    /*
    @Test
    public void canRemoveAddress() {
        app.address().openHomePage();
        if (app.address().getCountAddress() == 0) {
            app.address().createAddress(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }
        var oldAddress = app.address().getList();
        //System.out.println(oldAddress);
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());
        app.address().removeAddress(oldAddress.get(index));
        var newAddress = app.address().getList();
        //System.out.println(newAddress);
        var expectedList = new ArrayList<>(oldAddress);
        //System.out.println(expectedList);
        expectedList.remove(index);
        Assertions.assertEquals(newAddress, expectedList);
    }

     */

    @Test
    void canRemoveAllAddressAtOnce() throws InterruptedException {
        if (app.hbm().getAddressCount() == 0) {
            app.hbm().createAddress(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }

        //Thread.sleep(1000);
        app.address().removeAllAddress();
        Assertions.assertEquals(0, app.hbm().getAddressCount());
    }

    /*
        @Test
    void canRemoveAllAddressAtOnce() {
        app.address().openHomePage();
        if (app.address().getCountAddress() == 0) {
            app.address().createAddress(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }
        app.address().removeAllAddress();
        Assertions.assertEquals(0, app.address().getCountAddress());
    }
     */



}
