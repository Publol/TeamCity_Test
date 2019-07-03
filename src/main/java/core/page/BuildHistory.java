package core.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuildHistory extends NavigatableToMainPage {

    private static final By MAIN_CONTENT =          By.xpath("//input[contains(@aria-label, 'Filter by agent name')]");
    private static final By BUILD_HISTORY_ROWS =    By.xpath("//div[contains(@class, 'Build__not-comment-wrapper--3y')]");


    public BuildHistory(WebDriver driver) {
        super(driver, MAIN_CONTENT);
    }

    public BuildHistoryRow getRowBy(int index) {
        return new BuildHistoryRow(driver.findElements(BUILD_HISTORY_ROWS).get(index - 1));
    }

    public int getBuildHistoryRowNubmer(){
        return driver.findElements(BUILD_HISTORY_ROWS).size();
    }

    public class BuildHistoryRow {

        private final By HISTORY_TEMPLATE =    By.xpath(".//span[contains(@class, 'MiddleEllipsis__searchable--UB')]");


        private WebElement historyRow;



        public BuildHistoryRow(WebElement historyRow) {
            this.historyRow = historyRow;
        }

        private WebElement getInnerElementBy(int index) {
            return historyRow.findElements(HISTORY_TEMPLATE).get(index);
        }

        public String getNumber(){
            return getInnerElementBy(0).getText();
        }

        public String getStatus(){
            return getInnerElementBy(1).getText();
        }


    }
}
