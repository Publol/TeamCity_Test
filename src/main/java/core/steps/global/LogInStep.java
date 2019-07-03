package core.steps.global;

import core.env.Environment;
import core.hooks.CucumberHooks;
import core.page.LoginPage;

public class LogInStep extends AbstractGlobalStep {

    private String username;
    private String password;

    public LogInStep(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    void execute() {
        LoginPage loginPage = LoginPage.launch(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogInButton();
    }
}
