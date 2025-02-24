package ru.stqa.geometry.figures;

public class Rectangle {
    //созданная ф-ия Create Method
    //добавляем код ф-ии
    public static void printRectangleArea(double a, double b) {
        var text = String.format("Площадь прямоугольника со сторонами %f и %f = %f", a, b, rectangleArea(a, b));
        System.out.println(text);
    }

    //Create method
    private static double rectangleArea(double a, double b) {
        return a * b;
    }
}
