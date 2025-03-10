package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {
    protected WebDriver driver;

    private LoginHelper session;

    private GroupHelper groups;

    public void init() {
        if (driver == null) {
            driver = new ChromeDriver(); //запуск браузера через драйвер
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
