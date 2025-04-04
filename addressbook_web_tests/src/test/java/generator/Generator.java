package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.AddressData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-c"})
    int count;




    public static void main(String[] args) throws IOException {
        var generator = new Generator(); //замена вызова статик ф-и на метод созданного объекта
        JCommander.newBuilder()         // какие данные будим генерировать - данные для создания группы или контактов
                .addObject(generator)            //генерировать объекты типа GroupData или AddressData
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("address".equals(type)) {
            return generatorContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 10))
                    .withHeader(CommonFunctions.randomString(i * 10))
                    .withFooter(CommonFunctions.randomString(i * 10)));
        }
        return result;
    }

    private Object generatorContacts() {
        var result = new ArrayList<AddressData>();
        for (int i = 0; i < count; i++) {
            result.add(new AddressData()
                    .withFirstName(CommonFunctions.randomString(i * 10))
                    .withLastName(CommonFunctions.randomString(i * 10))
                    .withAddress(CommonFunctions.randomString(i * 10))
                    .withHome(CommonFunctions.randomString(i * 10))
                    .withEmail(CommonFunctions.randomString(i * 10))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
                    //withPhoto("src/test/resources/images/man.png"));

        }
        return result;
    }


    //сохранение строки в файл
    private void save(Object data) throws IOException {
        if ("json".equals(format)) { //https://github.com/FasterXML/jackson-databind
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT); //вывод в более удобном виде (построчно и т.д.)
            var json = mapper.writeValueAsString(data); //преобразование в формат JSON - в виде строки

            try (var writer = new FileWriter(output)) {
                writer.write(json);
            }
        } else if ("yaml".equals(format)) { //https://github.com/FasterXML/jackson-dataformats-text/tree/master/yaml
            ObjectMapper mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } else if ("xml".equals(format)) { //https://github.com/FasterXML/jackson-dataformat-xml
            ObjectMapper mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат " + format);
        }

    }


}
