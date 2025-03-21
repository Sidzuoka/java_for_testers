package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.ArrayList;
import java.util.Comparator;
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
                    result.add(new GroupData()
                            .withName(name)
                            .withHeader(header)
                            .withFooter(footer));
                }
            }
        }
        for (int i = 0; i < 5; i++ ){
            //длина строки i=0 -> входные данные могут дублироваться, т.к. рандомно подбираются
            result.add(new GroupData()
                    .withName(randomString(i * 10))
                    .withHeader(randomString(i * 10))
                    .withFooter(randomString(i * 10)));
        }
        return result;

    }


    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        //сравниваем списки
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        //после операции получаем новую группу
        var newGroups = app.groups().getList();
        //добавляем в конец ожидаемого списка группу

        //порядок элементов в NewGroup и ExpectedList не совпадает, новая группа после добавления может быть в середине списка
        //упорядочиваем списки по идентификаторам
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        //в ExpectedList идентификатор новой группе был присвоен после создания
        //возьмем последний элемент в списке newGroups - элемент с самым большим id
        //можем так сделать, id новой группе присваивается по возрастанию следующим числом
        //мы не ищем фактический id, а прогнозируем его
        var expectedList = new ArrayList<>(oldGroups);
        //создавать будет группа с непустыми/правильными Header и Footer, но перед сравнением мы их очистим
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

    }


    public static List<GroupData> negativeGroupProvider() {
        //1 - негативный - name' - когда не можем создать группу
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "group name'", "", "")));
        return result;

    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);

    }


}
