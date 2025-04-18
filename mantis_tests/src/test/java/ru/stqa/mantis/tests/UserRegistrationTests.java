package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        // JamesHelper ---- создать пользователя (адрес) на почтовом сервере - с адресом, с кот. будим регистрировать на багтрекере Мантис
        app.jamesCli().addUser(
                String.format("%s@localhost", CommonFunctions.randomString(8)),
                "password");

        // КлассПомощник ---- браузер ---- заполняем форму создания и отправляем - открываем браузер и заполняем форму создания


        // MailHelper ---- получаем/ждем почту (письмо только одно, адрес только что созданный) - на указанный адрес уходит письмо
        // извлекаем ссылку из полученного письма
        canExtractUrl();
        // КлассПомощник браузер ---- проходим по ссылке и завершаем регистрацию пользователя - возвращаемся в браузер
        // HttpSessionHelper ----- проверяем, что пользователь может залогиниться - проверить что пользователь действительно создан, и он может входить в систему
    }
}
