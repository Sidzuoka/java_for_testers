package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

        @Test
        void cannotCreateTriangleWithNegativeSide() {
            try {
                new Triangle(-1.0, 2.0, 3.0);
                Assertions.fail(); //если не получили исключение, то тест должен упасть
            } catch (IllegalArgumentException exception) {
                //если попали в catch, то все хорошо
            }
        }

        @Test //аннотация для запуска теста
        void canCalculatePerimeter() {
        //вызываем проверямый м-д и сохраняем его
            var tr = new Triangle(5.0, 7.0, 8.0);
            double result = tr.calculatePerimeter();
            Assertions.assertEquals(20.0, result);
        }

        @Test
        void canCalculateSquare() {
             Assertions.assertEquals(17.320508075688775, new Triangle(5.0, 7.0, 8.0).calculateSquare());
        }



}



