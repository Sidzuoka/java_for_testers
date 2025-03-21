package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase{

    @Test
    void canModifyGroup() {
        //проверяем, что кол-во групп не равняется нулю, если равняется, создаем новую группу
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        //группа, кот.ю хотим модифицировать (oldGroups); данные, кот. хотим наполнять форму testData
        var testData = new GroupData().withName("modified name");
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.groups().getList();
        //строим ожидаемое значение
        var expectedList = new ArrayList<>(oldGroups);
        //идентификатор группы, которую мы модифицировали
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        //отсортируем по модификаторам список групп до и после модификации (были отсортированы по имени)
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
            //id - строки, а не числа -> Integer.parseInt(o1.id())
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList); //сравниваем два списка - ожидаемый и реальный
    }
}
