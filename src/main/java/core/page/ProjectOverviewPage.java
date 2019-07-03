package core.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProjectOverviewPage extends AbstractPage {

    private static final By LYT_OVERVIEW_MAIN =         By.id("breadcrumbsWrapper");
    private static final By LNK_PROJECT_SETTINGS =      By.xpath(".//div[contains(@class, 'toolbarItem')]/a[@title='Edit project settings']");

    public ProjectOverviewPage(WebDriver driver) {
        super(driver, LYT_OVERVIEW_MAIN);
    }

    public ProjectGeneralSettings clickToEditProjectSettings(){
        WebElement lyt = waitUntilElementIsPresents(LYT_OVERVIEW_MAIN);
        waitUntilElementIsPresents(lyt, LNK_PROJECT_SETTINGS).click();
        return new ProjectGeneralSettings(driver);
    }
}
