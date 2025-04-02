package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        //13 групп создаст
        //8 - комбинации 2^3 из вложенных циклов
        //5 - циклом сгенерированные со случайными значениями

        var result = new ArrayList<GroupData>();

        /*
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

         */


        //сгенерированные рандомные тестовые данные из файла

        /*
        var json = "";

        try (var reader = new FileReader("groups.json");
            var breader = new BufferedReader(reader)
        ) {
            var line = breader.readLine();
            while (line != null) {
                json = json + line;
                line = breader.readLine();
            }
        }


         */

        /*
        //считывание файла формата json
        var json = Files.readString(Paths.get("groups.json")); //строка из файла
        ObjectMapper mapper = new ObjectMapper();

        var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {}); //{} - пустая реализация,
        result.addAll(value); //добавить в список все из файла                                                                                                            // класс, у кот. ничего нет, только декларация
        return result;


         */
        //считывание файла формата xml
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>() {});
        result.addAll(value);
        return result;
    }

    public static List<GroupData> singleRandomGroup() {
        return List.of(new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10)));
    }


    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroupHBM(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size() - 1).id();

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);



    /*

    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroupJDBC(GroupData group) {
        var oldGroups = app.jdbc().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.jdbc().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size() - 1).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);//сравнение происходит по всем столбцам name, header, footer - раньше header, footer обнуляли, т.к. не могли получить из GUI

        /*
        //в GUI тот же список групп, что и в БД
        var newUiGroups = app.groups().getList();
        newUiGroups.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        newUiGroups.sort(compareById);
        System.out.println(newUiGroups);
        System.out.println(newGroups);
        Assertions.assertEquals(newGroups, newUiGroups);


         */

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




        /*
    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

    }


     */



}
