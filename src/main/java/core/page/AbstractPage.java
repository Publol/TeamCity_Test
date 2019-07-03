package core.page;

import core.env.EnvConstants;
import core.selenium.SelCoreUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AbstractPage {
    private Logger logger = Logger.getLogger(AbstractPage.class);

    protected WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        waitForPageLoaded();
    }

    protected AbstractPage(WebDriver driver, By rootElement) {
        this(driver);
        waitForPageLoaded();
        waitUntilElementIsPresents(rootElement);
    }

    protected void waitForPageLoaded() {
        new WebDriverWait(getWebDriver(), Integer.valueOf(EnvConstants.PAGE_LOAD_TIMEOUT))
                .pollingEvery(2, TimeUnit.SECONDS)
                .until((ExpectedCondition<Boolean>) wd -> isComplete((JavascriptExecutor) wd));

    }

    protected WebElement waitUntilElementIsPresents(By by){
        return waitUntilElementIsPresents(by, Integer.valueOf(EnvConstants.PAGE_LOAD_TIMEOUT));
    }

    protected WebElement waitUntilElementIsPresents(WebElement parentElement, By by){
        return waitUntilElementIsPresents(parentElement, by, Integer.valueOf(EnvConstants.PAGE_LOAD_TIMEOUT));
    }

    protected WebElement waitUntilElementIsPresents(WebElement parentElement, By by, int timeout){
        logger.info("Wait until " + by.toString() + " element is presents for " + timeout + " - seconds...");
        try {
            parentElement.getText();
            new WebDriverWait(getWebDriver(), timeout)
                    .withMessage("Trying to locate the " + by.toString() + " - element")
                    .until((driver)->{
                        WebElement element = parentElement.findElement(by);
                        return element.isDisplayed() && element.isEnabled();
                    });
        } catch (Exception ex) {
            logger.info(parentElement.toString() + " - element is not presented anymore");
        }
        waitUntilAsyncTasksAreCompleted();
        return parentElement.findElement(by);
    }

    protected void waitUntilElementIsNotPresented(By by, int timeout) {
        new WebDriverWait(getWebDriver(), timeout)
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void waitUntilElementIsNotPresented(By by) {
        waitUntilElementIsNotPresented(by, Integer.valueOf(EnvConstants.PAGE_LOAD_TIMEOUT));
    }

    protected WebElement waitUntilElementIsPresents(By by, int timeout){
        logger.info("Wait until " + by.toString() + " element is presents for " + timeout + " - seconds...");

        try {
            new WebDriverWait(getWebDriver(), timeout)
                    .withMessage("Trying to locate the " + by.toString() + " - element")
                    .until((driver)->{
                        WebElement element = driver.findElement(by);
                        return element.isDisplayed() && element.isEnabled();
                    });
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NoSuchElementException(e.getMessage());
        }
        logger.error(by + " - WebElement was successfully found in DOM.");
        return getWebDriver().findElement(by);
    }

    protected void waitUntilAsyncTasksAreCompleted(){
        SelCoreUtils.waitUntilAsyncTasksAreCompleted(getWebDriver());
    }

    protected void refreshPageAndWait(){
        driver.navigate().refresh();
        waitForPageLoaded();
    }

    protected boolean isComplete(JavascriptExecutor wd) {
        return SelCoreUtils.isComplete(wd);
    }

    protected WebDriver getWebDriver(){
        return driver;
    }

    public String getTitle(){
        return getWebDriver().getTitle();
    }


}
