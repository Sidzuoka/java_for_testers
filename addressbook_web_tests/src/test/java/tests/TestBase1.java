package tests;

import manager.AppMng;
import org.junit.jupiter.api.BeforeEach;

public class TestBase1 {

    protected static AppMng appmng;

    @BeforeEach
    public void setUp() {
        if (appmng == null) {
            appmng = new AppMng();
        }
        appmng.init1();
    }

}
