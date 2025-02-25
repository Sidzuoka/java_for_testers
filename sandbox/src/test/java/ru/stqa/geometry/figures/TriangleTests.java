package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

            @Test //аннотация для запуска теста
            void canCalculatePerimeter() {
                //выызываем проверямый м-д и сохраняем его
                var tr = new Triangle(5.0, 7.0, 8.0);
                double result = tr.calculatePerimeter();
                Assertions.assertEquals(20.0, result);
            }

            @Test
            void canCalculateSquare() {
                Assertions.assertEquals(17.320508075688775, new Triangle(5.0, 7.0, 8.0).calculateSquare());
            }
}



