package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    protected static ApplicationManager app; //глобальная ссылка

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }

        app.init();
    }

}
