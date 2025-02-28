import java.io.File;

public class Hello {
    public static void main(String[] args) {
    //код будет совершать бессмысленное действие -> ловим его в исключенияе
        try {
            var z = calculate();
            System.out.println(z);
            System.out.println("Hello, world!");
        } catch (ArithmeticException exception) {
            //код выполниться, кода возникнет исключение
            //System.out.println(exception.getMessage()); // / by zero - сам выведет - только msg
            exception.printStackTrace(); // msg: / by zero + стек вызовов
        }



    }

    private static int calculate() {
        var x = 1;
        var y = 0;
        var z = divide(x, y);
        return z;
    }

    private static int divide(int x, int y) {
        var z = x / y;
        return z;
    }
}
