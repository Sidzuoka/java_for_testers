package ru.stqa.geometry.figures;

public record Square(double side) {

    //конструктор на проверку условий и выброс исключений
    public Square {
        if (side < 0) { //side значение параметра, переданного в конструктор, при его вызове
            throw new IllegalArgumentException("Square side should be non-negative");
        }
    }

    /***
        static void printSquareArea(double a) {
            System.out.println("Площадь квадрата со стороной"  + a + " = " + (a * a));
        }
    ***/


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
