package core.page;

import core.env.Environment;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Objects;

public class MainPage extends NavigatableToMainPage {

    private static final By MAIN_ELEMENT =          By.id("toolbar");
    private static final By PNL_USER_PANEL =        By.id("userPanel");
    private static final By LNK_ADMINISTRATION =    By.xpath("//div[contains(@class, 'info admin')]/a");
    private static final By LYT_PROJECT =           By.id("overviewMainInner");
    private static final By LT_PROJECT_LIST =       By.xpath(".//div[contains(@id, 'p_project')]");

    private static final String PROJECT_BY_TEXT =   ".//a[text()='%s']";

    public MainPage(WebDriver driver) {
        super(driver, MAIN_ELEMENT);
    }

    private WebElement getUserPanel(){
        return waitUntilElementIsPresents(PNL_USER_PANEL);
    }
    private WebElement getProjectLayout() { return waitUntilElementIsPresents(LYT_PROJECT); }

    public AdministrationPage navigateToAdministrationPage(){
        waitUntilElementIsPresents(getUserPanel(), LNK_ADMINISTRATION).click();
        return new AdministrationPage(driver);
    }

    public ProjectRow getProjectRowBy(int index) {
        List<WebElement> elements = getProjectLayout().findElements(LT_PROJECT_LIST);
        if (elements.isEmpty()) return null;
        return new ProjectRow(getProjectLayout().findElements(LT_PROJECT_LIST).get(index).findElement(By.xpath(".//a")), index, this);
    }

    public ProjectRow getProjectRowBy(String name) {
        String xPath = String.format(PROJECT_BY_TEXT, name);
        WebElement requiredElement;
        try {
            requiredElement = waitUntilElementIsPresents(By.xpath(xPath), Integer.valueOf(Environment.getProperty("global.hard_timeout")));
        }catch (NoSuchElementException nsee) {
            return null;
        }
        return new ProjectRow(requiredElement, name, this);
    }

    public class ProjectRow {
        private WebElement projectRow;
        private String projectName;
        private Integer projectIndex;
        private MainPage parentElement;

        private final By BTN_EXPAND_PROJECT = By.xpath("../span[contains(@id, 'blockHandleovr')]");
        private final By BTN_RUN_PROJECT =    By.xpath("ancestor::node()[3]//td[contains(@class, 'runButton')]/span/button[contains(text(), 'Run')]");
        private final By ICN_BUILD_STATUS_ICON = By.xpath("//span[contains(@class, 'buildDataIcon')]/span[contains(@id, 'runningIcon')]");
        private final By LT_PROJECT_EXPANDED =   By.xpath("ancestor::node()[8]//div[contains(@id, 'ph_project')]");
        private final String BTN_BUILD_NAME =    "ancestor::node()[8]//a[text()='%s']";

        ProjectRow(WebElement projectRow, String projectName, MainPage parentElement) {
            this.projectRow = projectRow;
            this.projectName = projectName;
            this.parentElement = parentElement;
        }

        ProjectRow(WebElement projectRow, int rowIndex, MainPage parentElement) {
            this.projectRow = projectRow;
            this.projectIndex = rowIndex;
            this.parentElement = parentElement;
        }

        public ProjectOverviewPage clickToProjectName(){
            projectRow.click();
            return new ProjectOverviewPage(driver);
        }

        public void runBuildByName(String buildName, boolean waitUntilEndOfBuild, int timeout) {
            WebElement elementByName = findElementByBuildName(buildName);
            waitUntilElementIsPresents(elementByName, BTN_RUN_PROJECT).click();

            refreshPageAndWait();

            if (waitUntilEndOfBuild) {
                waitUntilElementIsPresents(ICN_BUILD_STATUS_ICON);
                int i = 0;
                while (i < 10) {
                   refreshPageAndWait();
                    try {
                        waitUntilElementIsNotPresented(ICN_BUILD_STATUS_ICON, timeout / 10);
                        break;
                    } catch (TimeoutException ex) {
                        i++;
                    }
                }

            }
        }

        public void runBuildByName(String buildName) {
            runBuildByName(buildName, false, 0);
        }

        public void expandProject(){
            if (!isProjectExpanded()) {
                waitUntilElementIsPresents(projectRow, BTN_EXPAND_PROJECT).click();
                refreshRow();
            }
        }

        public boolean isProjectExpanded(){
            return waitUntilElementIsPresents(projectRow, LT_PROJECT_EXPANDED).getAttribute("class").contains("exp");
        }

        public BuildAdditionalInformation clickOnBuildBy(String buildName) {
            findElementByBuildName(buildName).click();
            return new BuildAdditionalInformation(driver);
        }

        public WebElement getInnerElement(){
            return projectRow;
        }

        private void refreshRow(){
            if (Objects.nonNull(projectName)) {
                projectRow = parentElement.getProjectRowBy(projectName).getInnerElement();
            } else {
                if (Objects.nonNull(projectIndex)) {
                    projectRow = parentElement.getProjectRowBy(projectIndex).getInnerElement();
                }
            }
        }

        private WebElement findElementByBuildName(String buildName){
            String pathToBuildName = String.format(BTN_BUILD_NAME, buildName);
            return waitUntilElementIsPresents(projectRow, By.xpath(pathToBuildName));
        }




    }

}
