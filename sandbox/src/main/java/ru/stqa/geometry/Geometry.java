package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle1; //используем класс, находящийся в другом пакете
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(5); //будет обращаться к supplier столько раз, сколько потребуется - 5 раз

        /*
        Consumer<Square> print = (square) -> {
            Square.printSquareArea(square);
            //Square.printPerimeter(square);
        }; //пишем код ф-ии без имени
        squares.forEach(print);

         */

        squares.peek(Square::printSquareArea); //.forEach(Square::printPerimeter);


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
