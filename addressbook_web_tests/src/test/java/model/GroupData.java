package model;

public record GroupData(String id, String name, String header, String footer) {

    //конструктор без параметров
    public GroupData() {
        this("", "", "", "");//набор пустых строк
    }

    public GroupData withName(String name) {
        return new GroupData(this.id, name, this.header, this.footer);
        //name - новый, header и footer, как у существующего объекта
    }

    public GroupData withId(String id) {
        return new GroupData(id, this.name, this.header, this.footer);
    }

    public GroupData withHeader(String name) {
        return new GroupData(this.id, this.name, header, this.footer);
    }

    public GroupData withFooter(String name) {
        return new GroupData(this.id, this.name, this.header, footer);
    }

}
