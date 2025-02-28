package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Test;

public class MathTests {

    @Test
    // тестовй фреймворк оборачивает в try/catch, искл-е выводится на экран и продолжается выполнение др. тестов
    void testDivideByZero() {
        var x = 1;
        var y = 0;
        var z = x / y;
        System.out.println(z);
    }
}
