import model.GroupData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        openGroupsPag();
        createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPag();
        createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        openGroupsPag();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("new name group");
        createGroup(groupWithName);
        //createGroup(new GroupData().withName("new name group"));
    }

}
