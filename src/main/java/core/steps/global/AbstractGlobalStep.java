package core.steps.global;

import core.hooks.CucumberHooks;
import core.steps.config.AbstractStepConfig;
import core.steps.config.VerifyProductExistsOnMainPageConfig;
import org.openqa.selenium.WebDriver;

import java.util.ArrayDeque;
import java.util.Queue;

public abstract class AbstractGlobalStep {

    protected WebDriver driver;
    protected AbstractStepConfig abstractStepConfig;
    protected AbstractGlobalStep(){

    }

    public void initHook(CucumberHooks hooks){
        this.driver = hooks.setUp();
    }

    abstract void execute();

}
