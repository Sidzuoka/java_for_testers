package ru.stqa.geometry.figures;

public class Triangle {
   private double a;
   private double b;
   private double c;

   public Triangle(double a, double b, double c) {
       this.a = a;
       this.b = b;
       this.c = c;
   }

   public double calculatePerimeter() {
      return this.a + this.b + this.c;
   }

   public double calculateSquare() {
       // Condition check over sides of triangle
           if (this.a < 0 || this.b < 0 || this.c < 0 || (this.a + this.b <= this.c)
                   || this.a + this.c <= this.b || this.b + this.c <= this.a)

           // Length of sides must be positive and sum of
           // any two sides must be smaller than third side
           {
               // print message if condition fails
               System.out.println("Not a valid side value");
               System.exit(0);
           }

           //else
            double p = calculatePerimeter()/2;
            return Math.sqrt(p*(p-this.a)*(p-this.b)*(p-this.c));
   }

    public static void printTriangleSquare(Triangle tr) {
        var text = String.format("Площадь треугольника со сторонами %f, %f, %f = %f", tr.a, tr.b, tr.c, tr.calculateSquare());
        System.out.println(text);
    }

}
