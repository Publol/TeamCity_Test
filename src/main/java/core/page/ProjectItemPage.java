package core.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProjectItemPage extends AdminContentPage {

    private static final By BTN_CREATE_PROJECT = By.xpath(".//p[contains(@class, createProject)]/a");

    protected ProjectItemPage(WebDriver driver) {
        super(driver);
    }

    public CreateProjectPage clickCreateProjectButton(){
        waitUntilElementIsPresents(getAdminContent(), BTN_CREATE_PROJECT).click();
        return new CreateProjectPage(driver);
    }
}
