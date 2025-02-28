package ru.stqa.geometry.figures;

public record Rectangle1(
        double a,
        double b
) {

    //Конструктор
    public Rectangle1 {
        //параметры не нужны, т.к. описаны в record
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Rectangle side should be non-negative");
        }

    }

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
