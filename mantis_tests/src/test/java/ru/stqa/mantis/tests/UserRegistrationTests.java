package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;

import java.util.ArrayList;
import java.util.List;

public class UserRegistrationTests extends TestBase {


    public static List<String> randomUsername() {
        return List.of(CommonFunctions.randomString(8));
    }


    @ParameterizedTest
    @MethodSource("randomUsername")
    public void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        // JamesHelper ---- создать пользователя (адрес) на почтовом сервере - с адресом, с кот. будим регистрировать на багтрекере Мантис
        app.jamesCli().addUser(
                String.format("%s@localhost", username),
                "password");

        // RegisterHelper - браузер ---- заполняем форму создания и отправляем - открываем браузер и заполняем форму создания
        app.register().canSignupNewAccount(username);

        // MailHelper ---- получаем/ждем почту (письмо только одно, адрес только что созданный) - на указанный адрес уходит письмо
        // извлекаем ссылку из полученного письма
        //canExtractUrl();
        // RegisterHelper - браузер ---- проходим по ссылке и завершаем регистрацию пользователя - возвращаемся в браузер
        // HttpSessionHelper ----- проверяем, что пользователь может залогиниться - проверить что пользователь действительно создан, и он может входить в систему
    }
}
