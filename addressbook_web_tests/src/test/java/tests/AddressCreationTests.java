package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddressCreationTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://localhost/addressbook/addressbook/");
        driver.manage().window().setSize(new Dimension(1050, 662));
        driver.findElement(By.name("user")).sendKeys("admin");
        driver.findElement(By.name("pass")).sendKeys("secret");
        driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'add new')]")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        //driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void canCreateAddress() {
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys("FirstName");
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).sendKeys("MiddleName");
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys("LastName");
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).sendKeys("Nickname");
        //driver.findElement(By.xpath("xpath=//input[@name='photo']")).click();
       // driver.findElement(By.name("photo")).sendKeys("C:\\TestingChalleng.txt");
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
        driver.findElement(By.name("bday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bday"));
            dropdown.findElement(By.xpath("//option[. = '1']")).click();
        }
        driver.findElement(By.name("bmonth")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bmonth"));
            dropdown.findElement(By.xpath("//option[. = 'January']")).click();
        }
        driver.findElement(By.name("byear")).click();
        driver.findElement(By.name("byear")).sendKeys("1980");
        driver.findElement(By.name("aday")).click();
       /* {
            WebElement dropdown = driver.findElement(By.name("aday"));
            dropdown.findElement(By.xpath("//option[. = '2']")).click();
        }
        driver.findElement(By.name("amonth")).click();
        {
            WebElement dropdown = driver.findElement(By.name("amonth"));
            dropdown.findElement(By.xpath("//option[. = 'January']")).click();
        }
        driver.findElement(By.name("ayear")).click();
        driver.findElement(By.name("ayear")).sendKeys("1990");
        driver.findElement(By.name("theform")).click();
        driver.findElement(By.name("new_group")).click();

        */
        {
            WebElement dropdown = driver.findElement(By.name("new_group"));
            dropdown.findElement(By.xpath("//option[. = 'modified name1']")).click();
        }
        driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        driver.findElement(By.linkText("home")).click();
    }

    @Test
    public void canCreateAddressWithEmptyField() {
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
        driver.findElement(By.name("bday")).click();
        driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        driver.findElement(By.linkText("home")).click();
    }

}
