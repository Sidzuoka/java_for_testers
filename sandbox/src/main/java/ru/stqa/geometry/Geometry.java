package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle1; //используем класс, находящийся в другом пакете
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.function.Consumer;

public class Geometry {
    public static void main(String[] args) {
        // var side = 7;
        // System.out.println("Площадь квадрата со стороной " + side + " = " + (side * side));

        var squares = List.of(new Square(7.0), new Square(5.0), new Square(3.0));
//        for (Square square : squares) {
//            Square.printSquareArea(square);
//        }

        //будет содержать ф-ю, кот-я печатает информацию о площади
        //принимает на вх. параметр, но ничего не возвращает
        Consumer<Square> print = (square) -> {
            Square.printSquareArea(square);
        }; //пишем код ф-ии без имени
        squares.forEach(print);


        /*

        //вызвали м-д printRectangleArea(a,b), теперь создаем на него ф-ии
        //пкм на м-д Create Method
        Rectangle1.printRectangleArea(3.0, 5.0);
        Rectangle1.printRectangleArea(8.0, 9.0);

       // Triangle tr = new Triangle(5.0, 7.0, 8.0);
       // System.out.println(tr.calculatePerimeter());
       // System.out.println(tr.calculateSquare());

        Triangle.printTriangleSquare(new Triangle(5.0, 7.0, 8.0));

         */

    }

}
