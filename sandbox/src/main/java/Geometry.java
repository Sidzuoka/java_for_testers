public class Geometry {
    public static void main(String[] args) {
        // var side = 7;
        // System.out.println("Площадь квадрата со стороной " + side + " = " + (side * side));

        printSquareArea(7.0);
        printSquareArea(5.0);
        printSquareArea(3.0);

        //вызвали м-д printRectangleArea(a,b), теперь создаем на него ф-ии
        //пкм на м-д Create Method
        printRectangleArea(3.0, 5.0);
        printRectangleArea(8.0, 9.0);


    }
    //созданная ф-ия Create Method
    //добавляем код ф-ии
    private static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + rectangleArea(a, b));
    }
    //Create method
    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    /***
        static void printSquareArea(double a) {
            System.out.println("Площадь квадрата со стороной"  + a + " = " + (a * a));
        }
    ***/
    
    //(a * a) -> Refactor -> Extract Method -> squareArea(a)
    // a -> Refactor -> Rename -> side
    static void printSquareArea(double side) {
        System.out.println("Площадь квадрата со стороной"  + side + " = " + squareArea(side));
    }

    //ф-ия на вход принимает параметр с типом данных double и возвращает double тип данных
    private static double squareArea(double a) {
        return a * a;
    }
}
