package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle; //используем класс, находящийся в другом пакете
import ru.stqa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        // var side = 7;
        // System.out.println("Площадь квадрата со стороной " + side + " = " + (side * side));

        Square.printSquareArea(7.0);
        Square.printSquareArea(5.0);
        Square.printSquareArea(3.0);

        //вызвали м-д printRectangleArea(a,b), теперь создаем на него ф-ии
        //пкм на м-д Create Method
        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(8.0, 9.0);


    }

}
