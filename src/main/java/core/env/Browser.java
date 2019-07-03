package core.env;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

public enum Browser {
    CHROME("src/main/resources/drivers/chromedriver.exe"),
    FIREFOX("src/main/resources/drivers/geckodriver.exe");

    String path;

    Browser(String path){
        this.path = path;
    }


    private MutableCapabilities getCapabilities(){
        switch (this) {
            case CHROME: {
                ChromeOptions chromeOptions = new ChromeOptions();
                //TODO additional chrome options
                return chromeOptions;
            }
            case FIREFOX: {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                //TODO additional firefox options
                return firefoxOptions;
            }
        }
        return new ChromeOptions();
    }

    public RemoteWebDriver getBrowser(){
        switch (this) {
            case CHROME: {
                ChromeDriverService driverService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(this.getPath()))
                        .usingAnyFreePort()
                        .build();

                ChromeDriver chromeDriver = new ChromeDriver(driverService, getCapabilities());
                return chromeDriver;
            }
            case FIREFOX: {
                GeckoDriverService driverService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File(this.getPath()))
                        .usingAnyFreePort()
                        .build();

                FirefoxDriver firefoxDriver = new FirefoxDriver(driverService, getCapabilities());

                return firefoxDriver;
            }
        }
        return new ChromeDriver();
    }

    public String getPath() {
        return path;
    }
}
