package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

public class AddressCreationTests extends TestBase1{


    @Test
    public void canCreateAddress() {
        if (!isElementPresent1(By.name("firstname"))) {
            driver.findElement(By.xpath("//a[contains(text(),'add new')]")).click();
        }
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

    @Test
    public void canCreateAddressWithEmptyField() {
        if (!isElementPresent1(By.name("firstname"))) {
            driver.findElement(By.xpath("//a[contains(text(),'add new')]")).click();
        }
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys("");
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).sendKeys("");
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys("");
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).sendKeys("");
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).sendKeys("");
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).sendKeys("");
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys("");
        driver.findElement(By.name("home")).click();
        driver.findElement(By.name("home")).sendKeys("");
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys("");
        driver.findElement(By.name("work")).click();
        driver.findElement(By.name("work")).sendKeys("");
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).sendKeys("");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys("");
        driver.findElement(By.name("email2")).click();
        driver.findElement(By.name("email2")).sendKeys("");
        driver.findElement(By.name("email3")).click();
        driver.findElement(By.name("email3")).sendKeys("");
        driver.findElement(By.name("homepage")).click();
        driver.findElement(By.name("homepage")).sendKeys("");
        driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        driver.findElement(By.linkText("home")).click();
    }

}
