package core.page;

import core.env.Environment;
import core.junit.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateProjectFromUrlPage extends AbstractPage {

    private static final By LYT_CREATE_PROJECT =        By.id("createProjectForm");
    private static final By TXF_PROJECT_NAME =          By.id("projectName");
    private static final By TXF_PROJECT_BUILD_NAME =    By.id("buildTypeName");
    private static final By BTN_PROCEED =               By.xpath("//input[contains(@class, 'btn btn_primary submitButton')]");
    private static final By MSG_ERROR =                 By.id("error_projectName");

    private static final String PAGE_TITLE =            "Create Project — TeamCity";

    public CreateProjectFromUrlPage(WebDriver driver) {
        super(driver, LYT_CREATE_PROJECT);
    }

    private WebElement getCreateProjectForm(){
        return waitUntilElementIsPresents(LYT_CREATE_PROJECT);
    }

    public void enterProjectName(String projectName){
        WebElement txf = waitUntilElementIsPresents(getCreateProjectForm(), TXF_PROJECT_NAME);
        txf.clear();
        txf.sendKeys(projectName);
    }

    public void enterBuildName(String buildName) {
        WebElement txf = waitUntilElementIsPresents(getCreateProjectForm(), TXF_PROJECT_BUILD_NAME);
        txf.clear();
        txf.sendKeys(buildName);
    }

    public ProjectBuildStepsPage clickProceedButton(){
        waitUntilElementIsPresents(getCreateProjectForm(), BTN_PROCEED).click();
        Assertions.assertFalseHard("Error should not presents ", isErrorPresents());
        return new ProjectBuildStepsPage(driver);
    }

    public boolean isErrorPresents(){
        try {
            waitUntilElementIsPresents(MSG_ERROR, Integer.valueOf(Environment.getProperty("global.hard_timeout")));
            return true;
        }catch (NoSuchElementException nsee) {
            if (getTitle().equals(PAGE_TITLE)) {
                return true;
            }
            return false;
        }
    }


}
