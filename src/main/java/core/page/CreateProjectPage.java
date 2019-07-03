package core.page;

import core.env.Environment;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateProjectPage extends AbstractPage {

    private static final By LYT_CREATE_FORM =       By.id("createFromUrlForm");
    private static final By BTN_PARENT_PROJECT =    By.className("-ufd-teamcity-ui-parentId");
    private static final String OPN_PARENT_PROJECT =   "//li[contains(text(), '%s')]";
    private static final By MSG_ERROR =             By.id("error_url");

    private static final By TXF_REPO_URL =          By.id("url");
    private static final By BTN_PROCEED =           By.name("createProjectFromUrl");
    private static final String PAGE_TITLE =        "Create Project — TeamCity";

    public CreateProjectPage(WebDriver driver) {
        super(driver, LYT_CREATE_FORM);
    }

    private WebElement getCreateForm(){
        return waitUntilElementIsPresents(LYT_CREATE_FORM);
    }

    public void selectParentProject(String projectName){
        waitUntilElementIsPresents(BTN_PARENT_PROJECT).click();

        String xPath = String.format(OPN_PARENT_PROJECT, projectName);
        waitUntilElementIsPresents(By.xpath(xPath)).click();
    }

    public void enterRepoUrl(String repoUrl){
        waitUntilElementIsPresents(TXF_REPO_URL).sendKeys(repoUrl);
    }

    public CreateProjectFromUrlPage clickProceedButton(){
        waitUntilElementIsPresents(BTN_PROCEED).click();
        return new CreateProjectFromUrlPage(driver);
    }

    public boolean isErrorPresents(){
        try {
            waitUntilElementIsPresents(MSG_ERROR, Integer.valueOf(Environment.getProperty("global.hard_timeout")));
            return false;
        }catch (NoSuchElementException nsee) {
            if (getTitle().equals(PAGE_TITLE)) {
                return true;
            }
            return false;
        }
    }

}
