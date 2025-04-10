package tests;
//проверки того, что информация в адресной книге представлена корректно
//GUI и инф-ия из БД соотв. друг другу

//extendes TestBase - получаем возможность пользоваться и инициализацией и возможность пользоваться всеми помощниками (БД в том числе)


import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressInfoTests extends TestBase{
    @Test
    void testPhones() {
        //предусловия - проверить, что список контактов не пустой
        if (app.hbm().getAddressCount() == 0) {
            app.hbm().createAddress(new AddressData("", "Firstname", "Lastname",
                    "Address", "HomeTelephone", "email", "", "", ""));
        }

        var addresses = app.hbm().getAddressList();
        var phones = app.address().getPhonesDict(); //id - возвращает значения столбеца All_phones - все телефоны, всех контактов - вычленный из строки кусок с телефонами
        //по очереди проверяем все контакты
        for (var address: addresses) {
            //пустые телефоны при склеивании нужно пропустить
            var expected = Stream.of(address.home(), address.mobile(), address.work(), address.secondary())
                    .filter(s -> s != null && ! "".equals(s)) //непустые оставляем
                    .collect(Collectors.joining("\n")); // собираем - \n - в качестве разделителя
            Assertions.assertEquals(expected, phones.get(address.id()));
        }


    }
}
