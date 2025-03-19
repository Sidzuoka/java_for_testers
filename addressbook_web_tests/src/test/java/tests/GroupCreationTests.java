package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.ArrayList;
import java.util.List;


public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() {
        //13 групп создаст
        //8 - комбинации 2^3 из вложенных циклов
        //5 - циклом сгенерированные со случайными значениями

        var result = new ArrayList<GroupData>();

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
        for (int i = 0; i < 5; i++ ){
            //длина строки i=0 -> входные данные могут дублироваться, т.к. рандомно подбираются
            result.add(new GroupData(randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;

    }


    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        //посчитать кол-во групп до выполнения операции
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        //после операции получаем новое значение
        int newGroupCount = app.groups().getCount();
        //проверяем, что кол-во групп изменилось
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }


    public static List<GroupData> negativeGroupProvider() {
        //1 - негативный - name' - когда не можем создать группу
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("group name'", "", "")));
        return result;

    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        //посчитать кол-во групп до выполнения операции
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        //после операции получаем новое значение
        int newGroupCount = app.groups().getCount();
        //проверяет, что кол-во групп не изменилось - т.к. не можем создать группу с name'
        Assertions.assertEquals(groupCount, newGroupCount);
    }


}
