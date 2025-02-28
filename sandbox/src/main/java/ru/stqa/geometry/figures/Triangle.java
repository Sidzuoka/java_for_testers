package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle (
        double a,
        double b,
        double c
) {

    public Triangle {
        // Condition check over sides of triangle
        if (a < 0 || b < 0 || c < 0)
        {
            // print message if condition fails
            throw new IllegalArgumentException("Triangle side should be non-negative ");
        }

        if (a + b <= c || a + c <= b || b + c <= a)
        {
            // print message if condition fails
            throw new IllegalArgumentException("Sum of any two sides must be smaller than third side");
        }


    }

   public double calculatePerimeter() {
      return a + b + c;
   }

   public double calculateSquare() {
            double p = calculatePerimeter()/2;
            return Math.sqrt(p*(p-a)*(p-b)*(p-c));
   }

    public static void printTriangleSquare(Triangle tr) {
        var text = String.format("Площадь треугольника со сторонами %f, %f, %f = %f", tr.a, tr.b, tr.c, tr.calculateSquare());
        System.out.println(text);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                || (Double.compare(this.a, triangle.c) == 0 && Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0)
                || (Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0 && Double.compare(this.c, triangle.a) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
