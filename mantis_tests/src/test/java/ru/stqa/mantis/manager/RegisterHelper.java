package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class RegisterHelper extends HelperBase {

    public RegisterHelper(ApplicationManager manager) {
        super(manager);
    }


    public void canSignupNewAccount(String username) {
        selectSignup();
        fillRegistrationForm(username);
        submitSignup();

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


    public void canCreateNewAccount() {
        fillUsernameAdmin();
        submitLogin();
        fillPasswordAdmin();
        submitLogin();
        submitManage();
        openMngUsersPage();
        initCreateNewAccount();
        fillUsernameNewAccount();
        fillRealnameNewAccount();
        fillEmailNewAccount();
        submitCreateUser();
    }


    public void startCreation(String user, String email) {
        if (!manager.session().isLoggedIn()) {
            manager.session().login(manager.property("web.username"), manager.property("web.password"));
        }
        manager.driver().get(String.format("%s/manage_user_create_page.php", manager.property("web.baseUrl")));
        type(By.name("username"), user);
        type(By.name("realname"), user);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }


    public void canCreateNewAccount1(String user, String email) {
        fillUsernameAdmin();
        submitLogin();
        fillPasswordAdmin();
        submitLogin();
        submitManage();
        openMngUsersPage();
        initCreateNewAccount();
        fillUsernameNewAccount1(user);
        fillRealnameNewAccount1(user);
        fillEmailNewAccount1(email);
        submitCreateUser();
    }

    private void fillUsernameNewAccount1(String user) {
        type(By.id("user-username"), user);
    }

    private void fillRealnameNewAccount1(String user) {
        type(By.id("user-realname"), user);
    }

    private void fillEmailNewAccount1(String email) {
        type(By.id("email-field"), email);
    }






    private void submitManage() {
        click(By.linkText("Manage"));
    }

    private void submitLogin() {
        click(By.cssSelector("input[value='Login']"));
    }

    private void submitCreateUser() {
        click(By.cssSelector("input[value='Create User']"));
    }


    private void fillEmailNewAccount() {
        type(By.id("email-field"), "username@localhost");
    }


    private void fillRealnameNewAccount() {
        type(By.id("user-realname"), "username");
    }


    private void fillUsernameNewAccount() {
        type(By.id("user-username"), "username");
    }

    private void initCreateNewAccount() {
        click(By.linkText("Create New Account"));
    }

    private void openMngUsersPage() {
        click(By.linkText("Manage Users"));
    }

    private void fillUsernameAdmin() {
        type(By.id("username"), "administrator");
    }

    private void fillPasswordAdmin() {
        type(By.id("password"), "root");
    }





    public void confirmRegistration(String url) {
        fillPassword(url);
        fillConfirmPassword();
        submitPasswordUpdate();
    }

    private void fillPassword(String url) {
        manager.driver().get(url);
        type(By.id("password"), "password");
    }

    private void fillConfirmPassword() {
        type(By.name("password_confirm"), "password");
    }

    private void submitPasswordUpdate() {
        click(By.cssSelector("button[type=submit]"));
    }





}
