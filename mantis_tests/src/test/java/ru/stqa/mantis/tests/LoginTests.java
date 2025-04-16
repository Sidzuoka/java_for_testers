package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AssertionFailureBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @Test
    void canLogin() {
        //предусловие - проверка, что логин еще не выполнен, а если выполнен, то надо сделать логаут
        app.session().login("administrator", "root");
        Assertions.assertTrue(app.session().isLoggedIn());

    }
}
