package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Generator {

    @Parameter(names={"--type", "t"})
    String type;

    @Parameter(names={"--output", "o"})
    String output;

    @Parameter(names={"--format", "f"})
    String format;

    @Parameter(names={"--count", "c"})
    String count;




    public static void main(String[] args) {
        var generator = new Generator(); //замена вызова статик ф-и на метод созданного объекта
        JCommander.newBuilder()         // какие данные будим генерировать - данные для создания группы или контактов
                .addObject(generator)            //генерировать объекты типа GroupData или AddressData
                .build()
                .parse(args);
        generator.run();
    }

    private void run() {
        var data = generate();
        save(data);
    }

    private Object generate() {
        return null;
    }

    private void save(Object data) {
    }


}
