package manager;

import org.openqa.selenium.By;

public class LoginHelper1 {

    private final AppMng appMng;

    public LoginHelper1(AppMng appMng) {
        this.appMng = appMng;
    }

    void login(String user, String password) {
        appMng.driver.findElement(By.name("user")).sendKeys(user);
        appMng.driver.findElement(By.name("pass")).sendKeys(password);
        appMng.driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    }
}
