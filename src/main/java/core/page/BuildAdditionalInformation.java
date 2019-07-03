package core.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BuildAdditionalInformation extends NavigatableToMainPage {

    private static final By TBL_TABS_TABLE =    By.className("tabsTable");
    private static final By BTN_HISTORY_TAB =   By.xpath("//li[contains(@id, 'buildTypeHistoryList_Tab')]/p/a");

    public BuildAdditionalInformation(WebDriver driver) {
        super(driver, TBL_TABS_TABLE);
    }

    public BuildHistory clickByBuildHistoryTab() {
        waitUntilElementIsPresents(BTN_HISTORY_TAB).click();
        return new BuildHistory(driver);
    }


}
