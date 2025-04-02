package manager.hbm;

//класс, который описывает объекты, соответствующие записям/столбцам в БД
//group_list - таблица, названия столбцов в точности соотв. названиям свойств
//если нет явного соотв-я, того используем аннотацию @Column

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
}
