package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class AddressRemovalTests extends TestBase1{


    @Test
    public void canRemoveAddress() {
        if (!isElementPresent1(By.linkText("home"))) {
            driver.findElement(By.linkText("home")).click();
        }
        if (!isElementPresent1(By.id("2"))) {
            driver.findElement(By.name("firstname")).click();
            driver.findElement(By.name("firstname")).sendKeys("FirstName");
            driver.findElement(By.name("middlename")).click();
            driver.findElement(By.name("middlename")).sendKeys("MiddleName");
            driver.findElement(By.name("lastname")).click();
            driver.findElement(By.name("lastname")).sendKeys("LastName");
            driver.findElement(By.name("nickname")).click();
            driver.findElement(By.name("nickname")).sendKeys("Nickname");
            driver.findElement(By.name("title")).click();
            driver.findElement(By.name("title")).sendKeys("Title");
            driver.findElement(By.name("company")).click();
            driver.findElement(By.name("company")).sendKeys("Company");
            driver.findElement(By.name("address")).click();
            driver.findElement(By.name("address")).sendKeys("Address");
            driver.findElement(By.name("home")).click();
            driver.findElement(By.name("home")).sendKeys("Home");
            driver.findElement(By.name("mobile")).click();
            driver.findElement(By.name("mobile")).sendKeys("Mobile");
            driver.findElement(By.name("work")).click();
            driver.findElement(By.name("work")).sendKeys("Work");
            driver.findElement(By.name("fax")).click();
            driver.findElement(By.name("fax")).sendKeys("Fax");
            driver.findElement(By.name("email")).click();
            driver.findElement(By.name("email")).sendKeys("email");
            driver.findElement(By.name("email2")).click();
            driver.findElement(By.name("email2")).sendKeys("email1");
            driver.findElement(By.name("email3")).click();
            driver.findElement(By.name("email3")).sendKeys("email2");
            driver.findElement(By.name("homepage")).click();
            driver.findElement(By.name("homepage")).sendKeys("homepage");
            driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
            driver.findElement(By.linkText("home")).click();
        }

        driver.findElement(By.id("2")).click();
        driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }

}
