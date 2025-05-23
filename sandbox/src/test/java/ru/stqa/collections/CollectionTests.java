package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionTests {

    @Test
    void arrayTests() {
        //var array = new String[] {"a", "b", "c"};
        var array = new String[3];
        Assertions.assertEquals(3, array.length);
        array[0] = "a";
        Assertions.assertEquals("a", array[0]);

        array[0] = "d";
        Assertions.assertEquals("d", array[0]);

    }

    @Test
    void ListTests() {
        var list = new ArrayList<String>(List.of("a", "b", "c", "a"));
        Assertions.assertEquals(4, list.size());
/*
        list.add("a");
        list.add("b");
        list.add("c");

        Assertions.assertEquals(3, list.size());

 */
        Assertions.assertEquals("a", list.get(0));

        list.set(0, "d");
        Assertions.assertEquals("d", list.get(0));
    }

    @Test
    void setTests() {
        //set - один раз в любом порядке
        var set = Set.copyOf(List.of("a", "b", "c", "a"));//copy - берем все элементы списка и формируем из этих элементов множества
        //var set = Set.of("a", "b", "c");
        //Assertions.assertEquals(3, set.size());
       // var element = set.stream().findAny().get();
        System.out.println(set);


        //var set = new HashSet<>(List.of("a", "b", "c", "a"));//вызвали конструктор HashSet, проинициал. листом
        //один раз упорядоченно, можно добавлять новые эл-ты
        //HashSet - от отличии от операций с .of - возвращает элемент, в кот-ый можно добавлять новые элементы
        //Assertions.assertEquals(3, set.size());

        //set.add("d");
        //Assertions.assertEquals(4, set.size());
        //System.out.println(set);


    }


    @Test
    void testMap() {
        var digits = new HashMap<Character, String>();
        digits.put('1', "one");
        digits.put('2', "two");
        digits.put('3', "three");
        Assertions.assertEquals("one", digits.get('1'));
        digits.put('1', "один");
        Assertions.assertEquals("один", digits.get('1'));
    }


}
