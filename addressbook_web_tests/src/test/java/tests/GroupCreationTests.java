package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.openGroupsPag();
        app.createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.openGroupsPag();
        app.createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.openGroupsPag();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("new name group");
        app.createGroup(groupWithName);
        //createGroup(new GroupData().withName("new name group"));
    }

}
