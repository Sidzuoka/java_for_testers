package manager;

import model.AddressData;
import org.openqa.selenium.By;

public class AddressHelper {

    private final AppMng appMng;

    public AddressHelper(AppMng appMng) {
        this.appMng = appMng;
    }

    public void openAddressPage() {
        if (!appMng.isElementPresent1(By.name("firstname"))) {
            appMng.driver.findElement(By.xpath("//a[contains(text(),'add new')]")).click();
        }
    }

    public boolean isAddressPresent() {
        openHomePage();
        return appMng.isElementPresent1(By.id("2"));
    }

    public void openHomePage() {
        if (!appMng.isElementPresent1(By.linkText("home"))) {
            appMng.driver.findElement(By.linkText("home")).click();
        }
    }

    public void createAddress(AddressData address) {
        openAddressPage();
        appMng.driver.findElement(By.name("firstname")).click();
        appMng.driver.findElement(By.name("firstname")).sendKeys(address.firstname());
        appMng.driver.findElement(By.name("lastname")).click();
        appMng.driver.findElement(By.name("lastname")).sendKeys(address.lastname());
        appMng.driver.findElement(By.name("address")).click();
        appMng.driver.findElement(By.name("address")).sendKeys(address.address());
        appMng.driver.findElement(By.name("home")).click();
        appMng.driver.findElement(By.name("home")).sendKeys(address.home());
        appMng.driver.findElement(By.name("email")).click();
        appMng.driver.findElement(By.name("email")).sendKeys(address.email());
        appMng.driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        appMng.driver.findElement(By.linkText("home")).click();
    }

    public void removeAddress() {
        openHomePage();
        appMng.driver.findElement(By.id("2")).click();
        appMng.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }
}
