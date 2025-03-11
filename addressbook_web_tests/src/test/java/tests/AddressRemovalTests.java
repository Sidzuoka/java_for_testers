package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;


public class AddressRemovalTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://localhost/addressbook/addressbook/");
        driver.manage().window().setSize(new Dimension(1050, 662));
        driver.findElement(By.name("user")).click();
        driver.findElement(By.name("user")).sendKeys("admin");
        driver.findElement(By.name("pass")).click();
        driver.findElement(By.name("pass")).sendKeys("secret");

    }

    @AfterEach
    public void tearDown() {
        //driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }

    @Test
    public void canRemoveAddress() {
        driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        driver.findElement(By.id("2")).click();
        driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();

    }
}
