package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class MailTests extends TestBase {

    //после отладки ф-ий, можно удалить
    @Test
    void canDrainInbox() {
        app.mail().drain("user1@localhost", "password");
    }

    //после отладки ф-ий, можно удалить
    @Test
    void canReceiveEmail() {
        var messages = app.mail().receive("user1@localhost", "password", Duration.ofSeconds(60));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);
    }

    //как извлечь ссылку из текста письма
    @Test
    void canExtractUrl() {
        //получаем почту
        var messages = app.mail().receive("user1@localhost", "password", Duration.ofSeconds(60));
        //берем текст первого письма, из кот. будим извлекать ссылку
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S+"); // \\S - НЕ пробел --- создали шаблон
        var matcher = pattern.matcher(text); // применили шаблон
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
    }
}
