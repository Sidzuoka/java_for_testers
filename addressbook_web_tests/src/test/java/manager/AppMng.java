package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AppMng {
    protected WebDriver driver;
    private LoginHelper1 session1;
    private AddressHelper address;

    public void init1(String browser) {
        if (driver == null) {
            if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else if("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknow browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/addressbook/");
            driver.manage().window().setSize(new Dimension(1050, 662));
            session1().login("admin", "secret");
        }
    }

    public LoginHelper1 session1() {
        if (session1 == null) {
            session1 = new LoginHelper1(this);
        }
        return session1; //возвращаем ссылку на этого помощника
    }

    public AddressHelper address() {
        if(address == null) {
            address = new AddressHelper(this);
        }
        return address;
    }

    public boolean isElementPresent1(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;

        }
    }

}
