package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.ArrayList;
import java.util.List;


public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() {
        //7 групп создаст
        //2 - фиксированные значения - group name, group name'
        //5 - циклом сгенерированные со случайными значениями
        var result = new ArrayList<GroupData>(List.of(
                new GroupData(),
                new GroupData().withName("new name group"),
                new GroupData("group name", "", ""),
                new GroupData("group name'", "", "")));
        for (int i = 0; i < 5; i++ ){
            result.add(new GroupData(randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
       /*
        // перебирает 2 значения name - пустой и непустой
        for (var name : List.of("", "group name")) {
            // перебирает 2 возможных значения header - пустой и непустой
            for (var header : List.of("", "group header")) {
            // для каждой пары name и header перебирает footer - пустой и непустой
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData(name, header, footer));
                }
            }
        }

        */


    }


    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        //посчитать кол-во групп до выполнения операции
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        //после операции получаем новое значение
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);

    }


}
