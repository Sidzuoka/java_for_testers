package manager;

import model.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager); //в конструктор передается ссылка на manager
    }

    public void openGroupsPage() {
        if (! manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }


    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void removeGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(null); //галочку поставили
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
    

    private void removeSelectedGroups() {
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

    private void selectGroup(GroupData group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroups();
    }

    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }


    public List<GroupData> getList() {
        //прочитать данные циклом из веб-приложения и построить список
        var groups = new ArrayList<GroupData>();
        //с тегом span, которые имют класс group
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        for (var span : spans) {
            var name = span.getText();
            //поиск не по всей странице, как при обращении к driver.findElements, а только внутри элемента span
            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getDomAttribute("value");
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
