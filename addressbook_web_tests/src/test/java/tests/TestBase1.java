package tests;

import model.AddressData;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase1 {

    protected static WebDriver driver;

    protected static void createAddress(AddressData address) {
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(address.firstname());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(address.lastname());
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys(address.address());
        driver.findElement(By.name("home")).click();
        driver.findElement(By.name("home")).sendKeys(address.home());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys(address.email());
        driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        driver.findElement(By.linkText("home")).click();
    }

    protected static void removeAddress() {
        driver.findElement(By.id("2")).click();
        driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new FirefoxDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/addressbook/");
            driver.manage().window().setSize(new Dimension(1050, 662));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        }
    }

    protected boolean isElementPresent1(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;

        }
    }

    protected void openAddressPage() {
        if (!isElementPresent1(By.name("firstname"))) {
            driver.findElement(By.xpath("//a[contains(text(),'add new')]")).click();
        }
    }

    protected void openHomePage() {
        if (!isElementPresent1(By.linkText("home"))) {
            driver.findElement(By.linkText("home")).click();
        }
    }

    protected boolean isAddressPresent() {
        return isElementPresent1(By.id("2"));
    }
}
