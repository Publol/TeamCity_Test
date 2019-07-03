package core.page;

import core.env.Environment;
import core.selenium.SelCore;
import core.selenium.SelCoreUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigatableToMainPage extends AbstractPage {

    protected NavigatableToMainPage(WebDriver driver) {
        super(driver);
    }

    protected NavigatableToMainPage(WebDriver driver, By rootLocator) {
        super(driver, rootLocator);
    }

    public static MainPage navigateToMainPage(WebDriver driver){
        driver.get(Environment.getProperty("global.server.url"));
        SelCoreUtils.waitUntilAsyncTasksAreCompleted(driver);
        SelCoreUtils.isComplete((JavascriptExecutor)driver);
        return new MainPage(driver);
    }
}
