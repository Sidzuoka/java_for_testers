package manager;

import org.openqa.selenium.By;

public class HelperBase1 {
    protected final AppMng appMng;

    public HelperBase1(AppMng appMng) {
        this.appMng = appMng;
    }

    protected void type(By locator, String text) {
        click(locator);
        appMng.driver.findElement(locator).clear();
        appMng.driver.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        appMng.driver.findElement(locator).click();
    }
}
