package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager); //в конструктор передается ссылка на manager
    }

    public void openGroupsPag() {
        if (! manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }


    public void createGroup(GroupData group) {
        openGroupsPag();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void removeGroup() {
        openGroupsPag();
        selectGroup();
        removeSelectedGroup();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPag();
        selectGroup(); //галочку поставили
        initGroupModification(); //кнопка EditGroup отжата
        fillGroupForm(modifiedGroup); // заполняем поля формы
        submitGroupModifiction(); // update кнопка
        returnToGroupsPage(); // жмем groupPage - возвращаемся на страницу со списком групп
    }
    
    private void submitGroupCreation() {
        click(By.name("submit"));
    }
    

    private void initGroupCreation() {
        click(By.name("new"));
    }
    

    private void removeSelectedGroup() {
        click(By.name("delete"));
    }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModifiction() {
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        // один и тот же метод и для создания и для модификации групп
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }

    public int getCount() {
        openGroupsPag();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
    }
}
