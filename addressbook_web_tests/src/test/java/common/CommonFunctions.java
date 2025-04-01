//В класс вынесен метод random, чтобы не делать TestBase (random) и Generator зависимыми друг от друга
package common;

import java.io.File;
import java.nio.file.Paths;
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

    public static String randomFile(String dir) {
        var fileNames =  new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();

    }
}
