package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GroupRemovalTests extends TestBase{


    @Test
    public void canRemoveGroup() {
        app.groups().openGroupsPage();
        //проверяем наличие заданного кол-ва групп, если нет, создаем новую
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        int groupCount = app.groups().getCount();

        app.groups().removeGroup();

        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);

    }

    @Test
    void canRemoveAllGroupsAtOnce() {
        //проверяем наличие заданного кол-ва групп, если нет, создаем новую
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());

    }

}
