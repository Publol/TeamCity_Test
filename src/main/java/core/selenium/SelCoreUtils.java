package core.selenium;

import core.env.EnvConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public final class SelCoreUtils {
    private static final Logger logger = Logger.getLogger(SelCoreUtils.class);

    public static boolean waitAndAcceptAlert(WebDriver driver){
        int i = 0;
        while (i < 20) {
            try {
                driver.switchTo().alert().accept();
                isComplete((JavascriptExecutor)driver);
                waitUntilAsyncTasksAreCompleted(driver);
                return true;
            } catch (Exception ex) {
                logger.info("Alert still not presented");
                i++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean isAlertPresented(WebDriver driver){
        int i = 0;
        while (i < 20) {
            try {
                driver.switchTo().alert();
                return true;
            } catch (Exception ex) {
                logger.info("Alert still not presented");
                i++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean isComplete(JavascriptExecutor wd) {
        return wd.executeScript("return document.readyState").equals("complete");
    }

    public static void waitUntilAsyncTasksAreCompleted(WebDriver driver){
        new WebDriverWait(driver, Integer.valueOf(Integer.valueOf(EnvConstants.PAGE_LOAD_TIMEOUT)))
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(JavascriptException.class)
                .until(dvr ->  (boolean) ((JavascriptExecutor) dvr).executeScript(
                        "if (typeof jQuery !== 'undefined') {return jQuery.active == 0;} return true;"));
    }
}
