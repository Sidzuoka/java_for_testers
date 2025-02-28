package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.Rectangle;

public class RectangleTests {

    @Test
    void cannotCreateRectangleWithNegativeSide() {
        try {
            new Rectangle1(-5.0, 3.0);
            Assertions.fail(); //если не получили исключение, то тест должен упасть
        } catch (IllegalArgumentException exception) {
            //если попали в catch, то все хорошо
        }
    }

    @Test
    void testEquality() {
        //равность сторон a 1ого и 2ого, b 1ого и 2ого
        var r1 = new Rectangle1(5.0, 4.0);
        var r2 = new Rectangle1(5.0, 4.0);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void testEquality2() {
        //равность сторон a 1ого и b 2ого, b 1ого и a 2ого
        var r1 = new Rectangle1(5.0, 4.0);
        var r2 = new Rectangle1(4.0, 5.0);
        Assertions.assertEquals(r1, r2);
    }

}
