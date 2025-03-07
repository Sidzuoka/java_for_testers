package model;

public record GroupData(String name, String header, String footer) {

    //конструктор без параметров
    public GroupData() {
        this("", "", "");//набор пустых строк
    }

    public GroupData withName(String name) {
        return new GroupData(name, this.header, this.footer);
        //name - новый,header и footer, как у существующего объекта
    }

    public GroupData withHeader(String name) {
        return new GroupData(this.name, header, this.footer);
    }

    public GroupData withFooter(String name) {
        return new GroupData(this.name, this.header, footer);
    }
}
