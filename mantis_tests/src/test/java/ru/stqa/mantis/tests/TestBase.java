package ru.stqa.mantis.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.mantis.manager.ApplicationManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    protected static ApplicationManager app; //глобальная ссылка

    @BeforeEach
    //инициализация начинается, когда начинает работать первый тест
    public void setUp() throws IOException {
        if (app == null) {
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target", "local.properties")));//.load - читаем данные из файла
            // используем системное св-во для выбора нужного нам файла конфигурации при запуске теста
            // target - целевая система, local.properties - если не указаны - то загружаем нстройки из конфиг. ф.
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "firefox"), properties);
        }
    }


}
