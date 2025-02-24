package ru.stqa.geometry.figures;

public class Square {
    /***
        static void printSquareArea(double a) {
            System.out.println("Площадь квадрата со стороной"  + a + " = " + (a * a));
        }
    ***/
    private double side;

    public Square(double side) {
        this.side = side;
    }


    //(a * a) -> Refactor -> Extract Method -> squareArea(a)
    // a -> Refactor -> Rename -> side
    public static void printSquareArea(Square s) {
        // для получения св-ва s.side
        String text = String.format("Площадь квадрата со стороной %f = %f", s.side, s.area());
        System.out.println(text);
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return this.side * 4;
    }
}
