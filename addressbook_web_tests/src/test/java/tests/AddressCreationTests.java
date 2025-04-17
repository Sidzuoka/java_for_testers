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

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }

        var group = app.hbm().getGroupList().get(0);

        if (app.jdbc().getAddressesWithoutGroup().size() == 0) {
            app.hbm().createAddress(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email", "", "", "", "", ""));
        }

        var oldRelated = app.hbm().getAddresssInGroup(group);
        var address = app.jdbc().getAddressesWithoutGroup();
        app.address().addAddressToGr(address.get(0), group);
        var newRelated = app.hbm().getAddresssInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }
/*
        //проверка на содержимое списков

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