package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import model.AddressData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class AddressCreationTests extends TestBase{


    public static List<AddressData> addressProvider() throws IOException {
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



        //сгенерированные рандомные тестовые данные из файла

        /*
        var json = "";

        try (var reader = new FileReader("address.json");
            var breader = new BufferedReader(reader)
        ) {
            var line = breader.readLine();
            while (line != null) {
                json = json + line;
                line = breader.readLine();
            }
        }


         */

        /*
        //считывание файла формата json
        var json = Files.readString(Paths.get("address.json")); //строка из файла
        ObjectMapper mapper = new ObjectMapper();

        var value = mapper.readValue(json, new TypeReference<List<AddressData>>() {}); //{} - пустая реализация,
        result.addAll(value); //добавить в список все из файла                                                                                                            // класс, у кот. ничего нет, только декларация
        return result;


         */
        //считывание файла формата xml
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("address.xml"), new TypeReference<List<AddressData>>() {});
        result.addAll(value);
        return result;

    }



    public static List<AddressData> negativeAddressProvider() {
        var result = new ArrayList<AddressData>(List.of(
                new AddressData("", "FirstName'", "", "", "", "", "", "", "", "", "")));
        return result;
    }

    public static List<AddressData> singleRandomAddress() {
        return List.of(new AddressData()
                .withLastName(CommonFunctions.randomString(10))
                .withFirstName(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(10))
                .withHome(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomString(10))
                .withPhoto("src/test/resources/images/man.png"));
    }


    @Test
    void canCreateAddressInGroup() {
        var address = new AddressData()
                .withLastName(CommonFunctions.randomString(10))
                .withFirstName(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(10))
                .withHome(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0); //get(0) выбирает первую группу из списка

        //получить список контактов, кот. входят в заданную гр.
        var oldRelated = app.hbm().getAddresssInGroup(group);
        app.address().createAddressGr(address, group);
        var newRelated = app.hbm().getAddresssInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());

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
    }


    @Test
    void canAddToGroupAddrr() {

        if (app.hbm().getAddressCount() == 0) {
            app.hbm().createAddress(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email", "", "", "", "", ""));
        }

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }


        var groupFirst = app.hbm().getGroupList().get(0);
        var addressFirst = app.hbm().getAddressList().get(0);

        if (app.hbm().getAddresssInGroup(groupFirst).isEmpty()) {
            app.address().addAddressToGr(addressFirst, groupFirst);
        }

        var address = app.jdbc().getIdAddressesInGroup();
        //System.out.println(address);


        if (!(address.isEmpty())) {
            for (var id : address) {
                    var oldRelated = app.hbm().getAddresssInGroup(groupFirst);
                    app.address().addAddressToGr(id, groupFirst);
                    var newRelated = app.hbm().getAddresssInGroup(groupFirst);
                    Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());

            }
        }





        /*
        //---если все адреса в первую группу добавлены, то при повторном запуске, добавит все адреса и во вторую группу тоже----
        var groupsSize = app.hbm().getGroupList().size();
        for (int i = 0; i < (groupsSize); i++) {
            var group = app.hbm().getGroupList().get(i);
                for (var addressInGroup : app.hbm().getAddresssInGroup(group)) {
                    for (var address : app.hbm().getAddressList()) {
                        if (!Objects.equals(addressInGroup.id(), address.id())) {
                            var oldRelated = app.hbm().getAddresssInGroup(group);
                            app.address().addAddressToGr(address, group);
                            var newRelated = app.hbm().getAddresssInGroup(group);
                            Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
                        }
                    }
                }

            }

         */
        }


    /* ------первая попытка-----две параллельные ветки------------------------------
            var groupsSize = app.hbm().getGroupList().size();
        for (int i = 0; i < (groupsSize); i++) {
            var group = app.hbm().getGroupList().get(i);
            if (!app.hbm().getAddresssInGroup(group).isEmpty()) {
                for (var addressInGroup : app.hbm().getAddresssInGroup(group)) {
                    //System.out.println(String.format("Контакт из группы " + addressInGroup));
                    for (var address : app.hbm().getAddressList()) {
                        //System.out.println(String.format("Контакт из списка контактов " + address));
                        if (!Objects.equals(addressInGroup.id(), address.id())) {
                            var oldRelated = app.hbm().getAddresssInGroup(group);
                            app.address().addAddressToGr(address, group);
                            var newRelated = app.hbm().getAddresssInGroup(group);
                            Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
                        }
                    }
                }
            } else {
                for (var address : app.hbm().getAddressList()) {
                    var oldRelated = app.hbm().getAddresssInGroup(group);
                    app.address().addAddressToGr(address, group);
                    var newRelated = app.hbm().getAddresssInGroup(group);
                    Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
                }

            }
        }
    }

     */






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


    }


 */












    @ParameterizedTest
    @MethodSource("singleRandomAddress")
    public void canCreateAddress(AddressData address) {
        var oldAddress = app.hbm().getAddressList();
        app.address().createAddress(address);
        var newAddress = app.hbm().getAddressList();
        Comparator<AddressData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAddress.sort(compareById);
        var maxId = newAddress.get(newAddress.size() - 1).id();

        var expectedList = new ArrayList<>(oldAddress);
        expectedList.add(address.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newAddress, expectedList);

    }


    /*
    //Jdbc_Address
    @ParameterizedTest
    @MethodSource("singleRandomAddress")
    public void canCreateAddress(AddressData address) {
        var oldAddress = app.jdbc().getAddressList();
        app.address().createAddress(address);
        var newAddress = app.jdbc().getAddressList();
        Comparator<AddressData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAddress.sort(compareById);
        var maxId = newAddress.get(newAddress.size() - 1).id();

        var expectedList = new ArrayList<>(oldAddress);
        expectedList.add(address.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newAddress, expectedList);

    }

     */


    /*
    @ParameterizedTest
    @MethodSource("singleRandomAddress")
    public void canCreateAddress(AddressData address) {

        var newUiAddress = app.address().getList();
        app.address().createAddress(address);
        var newAddress1 = new ArrayList<>(app.jdbc().getAddressList());
        newAddress1.add(address.withAddress("").withHome("").withEmail(""));

        Comparator<AddressData> compareById1 = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAddress1.sort(compareById1);
        var maxId1 = newAddress1.get(newAddress1.size() - 1).id();

        var expectedList1 = new ArrayList<>(newUiAddress);
        expectedList1.add(address.withId(maxId1));
        expectedList1.sort(compareById1);
        System.out.println(expectedList1);
        System.out.println(newAddress1);
        Assertions.assertEquals(newAddress1, expectedList1);
    }

     */



    /*
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
                .withAddress("")
                .withHome("")
                .withEmail("")
                .withPhoto("src/test/resources/images/man.png"));
        expectedList.sort(compareById);
        Assertions.assertEquals(newAddress, expectedList);
    }

     */



    @Test
    void canCreateOneAddress() {
        var address = new AddressData()
                .withLastName(CommonFunctions.randomString(10))
                .withFirstName(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(10))
                .withHome(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        app.address().createAddress(address);

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