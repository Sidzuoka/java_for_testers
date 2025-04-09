package tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class GroupModificationTests extends TestBase{

    @Test
    void canModifyGroup() {
        //проверяем, что кол-во групп не равняется нулю, если равняется, создаем новую группу
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        //группа, кот.ю хотим модифицировать (oldGroups); данные, кот. хотим наполнять форму testData
        var testData = new GroupData().withName(CommonFunctions.randomString(10));
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hbm().getGroupList();
        //строим ожидаемое значение
        var expectedList = new ArrayList<>(oldGroups);
        //идентификатор группы, которую мы модифицировали
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        //отсортируем по модификаторам список групп до и после модификации (были отсортированы по имени)

        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList)); //сравниваем два списка - ожидаемый и реальный
    }
}
