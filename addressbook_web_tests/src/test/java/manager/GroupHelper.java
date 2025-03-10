package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper {
    private final ApplicationManager manager;

    public GroupHelper(ApplicationManager manager) { //в конструктор передается ссылка на manager
        this.manager = manager;
    }

    public void openGroupsPag() {
        if (! manager.isElementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    public boolean isGroupPresent() {
        openGroupsPag();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createGroup(GroupData group) {
        openGroupsPag();
        manager.driver.findElement(By.name("new")).click();
        manager.driver.findElement(By.name("group_name")).click();
        manager.driver.findElement(By.name("group_name")).sendKeys(group.name());
        manager.driver.findElement(By.name("group_header")).click();
        manager.driver.findElement(By.name("group_header")).sendKeys(group.header());
        manager.driver.findElement(By.name("group_footer")).click();
        manager.driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        manager.driver.findElement(By.name("submit")).click();
        manager.driver.findElement(By.linkText("groups")).click();
    }

    public void removeGroup() {
        openGroupsPag();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.name("delete")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }
}
