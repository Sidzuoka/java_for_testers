package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

//помощники будут обращаться к драйверу через м-д драйвер Webdriver driver(), а не прямым чтением переменной private Webdriver driver

public class ApplicationManager {

    private WebDriver driver;
    private String string;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private RegisterHelper registerHelper;
    private JamesApiHelper jamesApiHelper;


    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;

    }



    //м-д выполняет ленивую инициализацию, где вызвали, там и тогда запустили
    public WebDriver driver() {
        if (driver == null) {
            if ("firefox".equals(string)) {
                driver = new FirefoxDriver();
            } else if ("chrome".equals(string)) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknow browser %s", string));
            }

            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit)); //остановка браузера
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1050, 660));
        }
        return driver;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }


    public HttpSessionHelper http() {
        if (httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public JamesCliHelper jamesCli() {
        if (jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    //метод, кот. будет возвращать из local.properties URL
    public String property(String name) {
        return properties.getProperty(name);
    }


    //ссылка на помощника и метод, вып-ий ленивую инициализацию
    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }


    public RegisterHelper register() {
        if(registerHelper == null) {
            registerHelper = new RegisterHelper(this);
        }
        return registerHelper;
    }

    public JamesApiHelper jamesApi() {
        if (jamesApiHelper == null) {
            jamesApiHelper = new JamesApiHelper(this);
        }
        return jamesApiHelper;
    }




}


