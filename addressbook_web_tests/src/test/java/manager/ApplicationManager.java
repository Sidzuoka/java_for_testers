package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {

    protected WebDriver driver;

    private LoginHelper session;

    private GroupHelper groups;

    private AddressHelper address;



    public void init(String browser) {
        if (driver == null) {
            if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknow browser %s", browser));
            }

            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit)); //остановка браузера
            driver.get("http://localhost/addressbook/addressbook/");
            driver.manage().window().setSize(new Dimension(1050, 660));
            session().login("admin","secret");
        }
    }

    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }; //ссылка на login

    public GroupHelper groups() {
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public AddressHelper address() {
        if(address == null) {
            address = new AddressHelper(this);
        }
        return address;
    }


    protected boolean isElementPresent(By locator) {
        //если находит элемент -> возвращает его,
        //если не находит, выбрасывает исключение
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

}
