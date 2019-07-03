package core.hooks;

import core.env.Browser;
import core.selenium.SelCore;
import core.selenium.WebDriverFactory;
import core.steps.global.AbstractStepExecutor;
import cucumber.api.java.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class CucumberHooks extends SelCore {

    @Before
    public WebDriver setUp(){
        if (Objects.nonNull(driver)) {
            return driver;
        }
        String browser = environment.getProperty("global.default.browser");
        if (Objects.isNull(browser)) {
            browser = "chrome".toUpperCase();
        }

        Browser selBrowser;
        try {
            selBrowser = Browser.valueOf(browser);
        }catch (NullPointerException | IllegalArgumentException ex) {
            selBrowser = Browser.CHROME;
        }
        driver = WebDriverFactory.initWebDiver(selBrowser);
        steps = new AbstractStepExecutor();
        steps.setHooks(this);
        return driver;
    }

    public AbstractStepExecutor getStepsInstance(){
        return steps;
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }

}
