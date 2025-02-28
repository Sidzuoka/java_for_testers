package ru.stqa.geometry.figures;

public record Triangle (
        double a,
        double b,
        double c
) {

    public Triangle {
        // Condition check over sides of triangle
        if (a < 0 || b < 0 || c < 0
                || (a + b <= c) || a + c <= b || b + c <= a)
        {
            // print message if condition fails
            throw new IllegalArgumentException("Triangle side should be non-negative " +
                    "and sum of any two sides must be smaller than third side");
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

}
