package manager;

import org.openqa.selenium.By;

public class LoginHelper1 extends HelperBase1{


    public LoginHelper1(AppMng appMng) {
        super(appMng);
    }

    void login(String user, String password) {
        type(By.name("user"), user);
        type(By.name("pass"), password);
        click(By.xpath("//input[@value=\'Login\']"));
    }
}
