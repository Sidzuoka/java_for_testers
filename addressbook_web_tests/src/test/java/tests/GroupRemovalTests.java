package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;


public class GroupRemovalTests extends TestBase{


    @Test
    public void canRemoveGroup() {
        app.groups().openGroupsPag();
        //проверяем наличие группы, если нет, создаем новую
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().removeGroup();
    }

}
