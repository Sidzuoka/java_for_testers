package manager.hbm;

//класс, который описывает объекты, соответствующие записям/столбцам в БД
//group_list - таблица, названия столбцов в точности соотв. названиям свойств
//если нет явного соотв-я, того используем аннотацию @Column
//фокусируется на том, как данные представлены в БД, на ур. приложения и маскируют от нас инф.

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import model.GroupData;

import java.util.Date;

@Entity
@Table(name = "group_list")
public class GroupRecord {

    @Id
    @Column(name = "group_id")
    public int id;

    @Column(name = "group_name")
    public String name;

    @Column(name = "group_header")
    public String header;

    @Column(name = "group_footer")
    public String footer;

    //т.к. в табл. не задано значение по дефолту, то можно задать текущее значение new Date()
    public Date deprecated = new Date(); //Date или можно использовать тип datetime (см. Структуру таблицы group_list SQL)



    public GroupRecord() {
    }

    public GroupRecord(int id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

}
