package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class RegisterHelper extends HelperBase {

    public RegisterHelper(ApplicationManager manager) {
        super(manager);
    }


    //2 - заполняем форму создания
    public void canSignupNewAccount(String username) {
        //openLoginPage();
        selectSignup();
        fillRegistrationForm(username);
        submitSignup();
        //returnToHomePage();
    }

    private void selectSignup() {
        click(By.linkText("Signup for a new account"));

    }

    private void fillRegistrationForm(String username) {
        type(By.id("username"), username);
        type(By.id("email-field"), "password");

    }

    private void submitSignup() {
        click(By.cssSelector("input[value=Signup]"));
    }


    /*
    private void openLoginPage() {
        if (!isElementPresent(By.linkText("Login"))) {
            returnToLoginPage();
        }
    }

    private void returnToLoginPage() {
        click(By.linkText("Login"));
    }


     */

    //проходим по ссылке и завершаем регистрацию



}
