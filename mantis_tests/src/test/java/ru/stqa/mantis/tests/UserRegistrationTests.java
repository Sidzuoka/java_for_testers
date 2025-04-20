package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @ParameterizedTest
    @ValueSource(strings = "username")
    public void canCreateAccount(String username) {
        var email = String.format("%s@localhost", username);
        app.jamesCli().addUser(
                String.format("%s@localhost", username),
                "password");
        //app.mail().drain(email, "password");
        app.register().canCreateNewAccount();
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(10));
        //Assertions.assertEquals(1, messages.size());
        //System.out.println(messages);
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S+"); // \\S - НЕ пробел --- создали шаблон
        var matcher = pattern.matcher(text); // применили шаблон
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            //System.out.println(url);
            app.register().confirmRegistration(url);
        }
        app.http().login(username, "password");
        Assertions.assertTrue(app.http().isLoggedIn());

    }












    public static List<String> randomUsername() {
        return List.of(CommonFunctions.randomString(8));
    }


    /*

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
        System.out.println(username);
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(60));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);

        // извлекаем ссылку из полученного письма


        //canExtractUrl();
        // RegisterHelper - браузер ---- проходим по ссылке и завершаем регистрацию пользователя - возвращаемся в браузер
        // HttpSessionHelper ----- проверяем, что пользователь может залогиниться - проверить что пользователь действительно создан, и он может входить в систему
    }


     */







    /*
        @Test
        public void canCreateAccount() {
        //app.register().canCreateNewAccount();
        var messages = app.mail().receive("username@localhost", "password", Duration.ofSeconds(60));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);

    }

     */


}
