package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import model.AddressData;
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
                new AddressData("", "FirstName'", "", "", "", "", "src/test/resources/images/man.png")));
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
                .withAddress("")
                .withHome("")
                .withEmail(""));
                //.withPhoto("src/test/resources/images/man.png"));
        expectedList.sort(compareById);
        Assertions.assertEquals(newAddress, expectedList);
    }

    @Test
    void canCreateOneAddress() {
        var address = new AddressData()
                .withLastName(CommonFunctions.randomString(10))
                .withFirstName(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(10))
                .withHome(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
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
