package tests;

import common.CommonFunctions;
import model.AddressData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


public class AddressRemovalTests extends TestBase{


    @Test
    public void canRemoveAddress() {
        if (app.hbm().getAddressCount() == 0) {
            app.hbm().createAddress(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"));
        }
        //System.out.println(app.hbm().getAddressCount());
        var oldAddress = app.hbm().getAddressList();
        //System.out.println(oldAddress);
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());
        app.address().removeAddress(oldAddress.get(index));
        var newAddress = app.hbm().getAddressList();
        var expectedList = new ArrayList<>(oldAddress);
        expectedList.remove(index);
        Assertions.assertEquals(newAddress, expectedList);
    }

    @Test
    void canRemoveFromGroupAddrr() {

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }

        var group = app.hbm().getGroupList().get(0);

        if (app.hbm().getAddresssInGroup(group).isEmpty()){
            app.address().createAddressGr(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email"), group);
        }


        var address = app.hbm().getAddressList().get(0);

        //получить список контактов, кот. входят в заданную гр.
        var oldRelated = app.hbm().getAddresssInGroup(group);
        app.address().removeAddressFromGr(address, group);
        var newRelated = app.hbm().getAddresssInGroup(group);
        Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());


        /*
        //проверка на содержимое списов

        Comparator<AddressData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        oldRelated.sort(compareById);
        var maxId = newRelated.get(newRelated.size() - 1).id();

        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(address.withId(maxId));
        expectedList.sort(compareById);
        System.out.println(newRelated);
        System.out.println(expectedList);
        Assertions.assertEquals(newRelated, expectedList);

         */


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
