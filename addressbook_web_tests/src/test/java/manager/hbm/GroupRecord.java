package manager.hbm;

//класс, который описывает объекты, соответствующие записям/столбцам в БД
//group_list - таблица, названия столбцов в точности соотв. названиям свойств
//если нет явного соотв-я, того используем аннотацию @Column
//фокусируется на том, как данные представлены в БД, на ур. приложения и маскируют от нас инф.

import jakarta.persistence.*;
import model.GroupData;

import java.util.Date;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY) //связь контакт может входить в несколько групп - по умол. LAZY - загружать в самый последний момент
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    public List<AddressRecord> addresses;


    public GroupRecord() {
    }

    public GroupRecord(int id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

}
