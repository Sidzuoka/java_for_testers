import java.io.File;

public class Hello {
    public static void main(String[] args) {

        System.out.println("Hello, world!");

        //Работа с файлом. Объектыю
        //Код проверяет существование ф. и выводит эту информацию на экран
        var configFile = new File("sandbox/build.gradle");
        //выведет инф-ю сущетвует ли такой класс
        System.out.println(configFile.getAbsoluteFile()); //выведет полный путь к файлу
        System.out.println(configFile.exists()); //true/false
        //ф-ия exists() вызывается в объекте configFile, и берет из него ф., инф-ю о кот. необходимо вывести
    }
}
