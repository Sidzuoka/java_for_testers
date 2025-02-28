package ru.stqa.geometry.figures;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        //приведение типов
        Rectangle1 that = (Rectangle1) o;
        return (Double.compare(this.a, that.a) == 0 && Double.compare(this.b, that.b) == 0)
                || (Double.compare(this.b, that.a) == 0 && Double.compare(this.a, that.b) == 0);
        //сторона а 1ого совпадает со b стороной 2ого, а
        //сторона b 1ого совпадает со a стороной 2ого
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
        //return 1;
    }

    //Create method
    private static double rectangleArea(double a, double b) {
        return a * b;
    }
}
