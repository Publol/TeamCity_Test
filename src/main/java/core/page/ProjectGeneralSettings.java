package core.page;

import core.enums.ProjectActionOption;
import core.selenium.SelCoreUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProjectGeneralSettings extends NavigatableToMainPage {

    private static final By LYT_OVERVIEW_MAIN =         By.id("breadcrumbsWrapper");
    private static final By DDN_ACTIONS =               By.xpath(".//button[contains(@class, 'btn btn_mini popupLink ')]");

    public ProjectGeneralSettings(WebDriver driver) {
        super(driver, LYT_OVERVIEW_MAIN);
    }

    public void clickActionsAndSelect(ProjectActionOption option) {
        By xpathForAction = option.getXpathForAction();

        WebElement actionDropdown = waitUntilElementIsPresents(DDN_ACTIONS);
        actionDropdown.click();

        waitUntilElementIsPresents(xpathForAction).click();
    }

    public boolean isAlertPresented(){
        return SelCoreUtils.isAlertPresented(driver);
    }

    public ProjectGeneralSettings acceptDeleteAlert(){
        SelCoreUtils.waitAndAcceptAlert(driver);
        return new ProjectGeneralSettings(driver);
    }

}
