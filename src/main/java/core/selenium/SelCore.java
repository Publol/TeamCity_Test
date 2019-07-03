package core.selenium;

import core.env.Environment;
import core.steps.global.AbstractStepExecutor;
import org.openqa.selenium.WebDriver;

public abstract class SelCore {

    protected final Environment environment = Environment.INSTANCE;
    protected WebDriver driver;
    protected AbstractStepExecutor steps;

    public void maximizeWindow(){
        driver.manage().window().maximize();
    }

}
