package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;


public class GroupCreationTests extends TestBase {

    public static List<String> groupNameProvider() {
        var result = new ArrayList<String>();
        for (int i = 0; i < 5; i++ ){
            result.add(randomString(i * 10));
        }
        return result;

    }

    @ParameterizedTest
    @ValueSource(strings = {"group name", "group name'"})
    //имя группы будет параметром
    public void canCreateGroup(String name) {
        //посчитать кол-во групп до выполнения операции
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData(name, "group header", "group footer"));
        //после операции получаем новое значение
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);

    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("new name group"));
        //createGroup(new GroupData().withName("new name group"));
    }

    @ParameterizedTest
    @MethodSource("groupNameProvider")
    public void canCreateMultipleGroups(String name) {
        //посчитать кол-во групп до выполнения операции
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData( name, "group header", "group footer"));
        //после операции получаем новое значение
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);

    }


}
