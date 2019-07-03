package core.steps;

import core.env.Environment;
import core.hooks.CucumberHooks;
import core.steps.global.LogInStep;
import cucumber.api.java.en.Given;

public class BackgroupSteps extends AbstractStep {

    public BackgroupSteps(CucumberHooks cucumberHooks) {
        super(cucumberHooks);
    }

    @Given("^As user I wish to login into TeamCity$")
    public void as_user_I_wish_to_open_TeamCity_server() throws Throwable {
        String username = Environment.getProperty("global.username");
        String password = Environment.getProperty("global.password");

        steps
                .addStep(new LogInStep(username, password))
                .execute();
    }
}
