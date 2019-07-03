package core.selenium;

import core.env.Browser;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    public static WebDriver initWebDiver(Browser browser) {
        return browser.getBrowser();
    }


}
