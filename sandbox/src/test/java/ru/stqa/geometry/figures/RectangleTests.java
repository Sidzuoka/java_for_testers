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

}
