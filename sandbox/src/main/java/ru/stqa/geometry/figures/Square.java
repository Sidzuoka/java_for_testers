package ru.stqa.geometry.figures;

public class Square {
    /***
        static void printSquareArea(double a) {
            System.out.println("Площадь квадрата со стороной"  + a + " = " + (a * a));
        }
    ***/

    //(a * a) -> Refactor -> Extract Method -> squareArea(a)
    // a -> Refactor -> Rename -> side
    public static void printSquareArea(double side) {
        String text = String.format("Площадь квадрата со стороной %f = %f", side, area(side));
        System.out.println(text);
    }

    //ф-ия на вход принимает параметр с типом данных double и возвращает double тип данных
    public static double area(double a) {
        return a * a;
    }

    public static double perimeter(double a) {
        return 4 * a;
    }
}
