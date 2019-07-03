package core.page;

import core.env.Environment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage {

    private final static By LOGIN_FORM =       By.className("loginForm");
    private final static By TXF_USERNAME =     By.xpath("//input[contains(@id, 'username')]");
    private final static By TXF_PASSWORD =     By.xpath("//input[contains(@id, 'password')]");
    private final static By BTN_LOG_IN =       By.xpath("//input[contains(@name, 'submitLogin')]");

    public LoginPage(WebDriver driver) {
        super(driver, LOGIN_FORM);
    }

    public static LoginPage launch(WebDriver driver){
        driver.get(Environment.getProperty("global.server.url"));
        return new LoginPage(driver);
    }

    private WebElement getLoginForm(){
        return waitUntilElementIsPresents(LOGIN_FORM);
    }

    public void enterUsername(String username) {
        waitUntilElementIsPresents(getLoginForm(), TXF_USERNAME).sendKeys(username);
    }

    public void enterPassword(String password) {
        waitUntilElementIsPresents(getLoginForm(), TXF_PASSWORD).sendKeys(password);
    }

    public void clickLogInButton(){
        waitUntilElementIsPresents(getLoginForm(), BTN_LOG_IN).click();
    }



}
