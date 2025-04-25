package tests;
//проверки того, что информация в адресной книге представлена корректно
//GUI и инф-ия из БД соотв. друг другу

//extendes TestBase - получаем возможность пользоваться и инициализацией и возможность пользоваться всеми помощниками (БД в том числе)


import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressInfoTests extends TestBase{
    @Test
    void testPhones() {
        //предусловия - проверить, что список контактов не пустой----
        if (app.hbm().getAddressCount() == 0) {
            app.hbm().createAddress(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email", "", "", "", "", ""));
        }

        var addresses = app.hbm().getAddressList();
        var expected = addresses.stream().collect(Collectors.toMap(AddressData::id, address ->
            Stream.of(
                            address.home(),
                            address.mobile(),
                            address.work(),
                            address.secondary())
                    .filter(s -> s != null && ! "".equals(s)) //непустые оставляем
                    .collect(Collectors.joining("\n")) // склеиваем - \n разделитель
        ));
        var phones = app.address().getPhonesDict(); //id - возвращает значения столбеца All_phones - все телефоны, всех контактов - вычленный из строки кусок с телефонами
        //Сравниваем значения из БД с знач. из GUI
        Assertions.assertEquals(expected, phones);

        //System.out.println(expected);
        //System.out.println(phones);

    }


    @Test
    void testOneAddress() {
        //предусловия - проверить, что список контактов не пустой
        if (app.hbm().getAddressCount() == 0) {
            app.hbm().createAddress(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email", "", "", "", "", ""));
        }

        var oldAddress = app.hbm().getAddressList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());
        var addressOne = app.hbm().getAddressList().get(index);

        var address = app.address().getOneAddress(addressOne);


        var expected = Stream.of(
                        addressOne.id(),
                        addressOne.firstname(),
                        addressOne.lastname(),
                        addressOne.address(),
                        addressOne.email(),
                        addressOne.email2(),
                        addressOne.email3(),
                        addressOne.home(),
                        addressOne.mobile(),
                        addressOne.work(),
                        addressOne.secondary())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));

        //System.out.println(address);
        //System.out.println(expected);
        Assertions.assertEquals(expected, address);
    }
}
