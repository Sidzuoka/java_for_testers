package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Random;


public class GroupRemovalTests extends TestBase{

    //напрямую из БД
    @Test
    public void canRemoveGroup() {
        //проверяем наличие заданного кол-ва групп, если нет, создаем новую
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);

    }

    /*
    //из GUI
    @Test
    public void canRemoveGroup() {
        app.groups().openGroupsPage();
        //проверяем наличие заданного кол-ва групп, если нет, создаем новую
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);

    }

     */

    @Test
    void canRemoveAllGroupsAtOnce() {
        //проверяем наличие заданного кол-ва групп, если нет, создаем новую
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());

    }

}
