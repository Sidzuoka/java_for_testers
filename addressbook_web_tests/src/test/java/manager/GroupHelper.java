package manager;

import io.qameta.allure.Step;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager); //в конструктор передается ссылка на manager
    }

    public void openGroupsPage() {
        if (! manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }


    @Step
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

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(group); //галочку поставили
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
        click(By.linkText("groups"));
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
        manager.driver
                //серия вызовов - найти элементы и кликнуть по ним
                //consumer - применить ф-ию ко всем элементам списка
                .findElements(By.name("selected[]")) //1. найти элементы
                .forEach(WebElement::click); //2. для каждого элемента вызвать м-д click
                                            //цикл - проходится по элементам листа и кликает,
                                            // на вх. элементы стр., на выход ничего не возвращает
    }


    public List<GroupData> getList() {
        openGroupsPage();
        //прочитать данные циклом из веб-приложения и построить список
        //с тегом span, которые имют класс group
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        return spans.stream()
                .map(span -> { //на вх. принимает элемент - span, на вых. - {} - вернет объект типа GroupData
                    var name = span.getText();
                    //поиск не по всей странице, как при обращении к driver.findElements, а только внутри элемента span
                    var checkbox = span.findElement(By.name("selected[]"));
                    var id = checkbox.getDomAttribute("value");
                    return new GroupData().withId(id).withName(name); //строим объект, возвращаем его
                })
                .collect(Collectors.toList());
    }
}
