//В класс вынесен метод random, чтобы не делать TestBase (random) и Generator зависимыми друг от друга
package common;

import java.util.Random;

public class CommonFunctions {
    //если не сделать статическим, не сможем вызвать в GroupCreationTests
    //n - длина rnd Str - количество сгенерируемых символов
    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char)('a' + rnd.nextInt(26));
        }
        return result;
    }
}
