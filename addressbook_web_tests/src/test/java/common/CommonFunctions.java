//В класс вынесен метод random, чтобы не делать TestBase (random) и Generator зависимыми друг от друга
package common;

import java.io.File;
import java.nio.charset.CharacterCodingException;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    //если не сделать статическим, не сможем вызвать в GroupCreationTests
    //n - длина rnd Str - количество сгенерируемых символов
    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);//генерирует случайное число от 0 до 26
        var result = Stream.generate(randomNumbers)
                .limit(n) //не нужно бесконечное кол-во чисел - ограничиваем числом n - заданное кол-во символов
                //числа преоразует в символы
                .map(i -> 'a' + i) //1-ый трансформатор - код символа
                .map(Character::toString) //2-ой трансформатор - символ (код) в строку
                .collect(Collectors.joining());//поток строчек из одного символа собираем вместе - collect.joining - собирает все в строку
        return result;
    }

    public static String randomFile(String dir) {
        var fileNames =  new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();

    }
}
