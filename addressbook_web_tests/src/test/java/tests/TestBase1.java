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
        appmng.init1(System.getProperty("browser", "firefox"));
        // передали системное свойство в параметр метода
    }

}
