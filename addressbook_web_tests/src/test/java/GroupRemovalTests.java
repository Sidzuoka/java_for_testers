import org.junit.jupiter.api.Test;


public class GroupRemovalTests extends TestBase{


    @Test
    public void canRemoveGroup() {
        openGroupsPag();
        //проверяем наличие группы, если нет, создаем новую
        if (!isGroupPresent()) {
            createGroup("group name", "group header", "group footer");
        }
        removeGroup();
    }

}
