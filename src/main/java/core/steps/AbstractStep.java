package core.steps;

import core.hooks.CucumberHooks;
import core.steps.global.AbstractStepExecutor;
import org.openqa.selenium.WebDriver;

public class AbstractStep {

    protected CucumberHooks cucumberHooks;
    protected WebDriver driver;
    protected AbstractStepExecutor steps;

    protected AbstractStep(CucumberHooks cucumberHooks) {
        this.cucumberHooks = cucumberHooks;
        this.driver = this.cucumberHooks.setUp();
        this.steps =  this.cucumberHooks.getStepsInstance();
        this.cucumberHooks.maximizeWindow();
    }

}
