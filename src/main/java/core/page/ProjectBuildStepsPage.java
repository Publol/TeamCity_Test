package core.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class ProjectBuildStepsPage extends NavigatableToMainPage {

    private static final By MAIN_ELEMENT =          By.id("discoveredRunners");
    private static final By TBL_STEPS =             By.className("parametersTable");
    private static final By TR_STEPS_LIST =         By.xpath("./tbody/tr");
    private static final By INC_UPDATE_BAR =        By.id("discoveryProgressContainer");

    private static final By BTN_USE_SELECTED =      By.xpath("//a[contains(text(), 'Use selected')]");

    private static final String TH_STEP_NAME =      "./td[contains(text(), '%s')]";
    private static final String CBX_ADD_STEP =          "../tr[%s]/td/input";

    public ProjectBuildStepsPage(WebDriver driver) {
        super(driver, MAIN_ELEMENT);
    }

    private WebElement getStepsTable(){
        waitUntilAsyncTasksAreCompleted();
        return waitUntilElementIsPresents(TBL_STEPS);
    }

    private List<WebElement> getStepElements(){
        waitUntilElementIsNotPresented(INC_UPDATE_BAR);
        return getStepsTable().findElements(TR_STEPS_LIST);
    }

    public void checkStepBy(String stepName) {
        List<WebElement> stepElements = getStepElements();
        String xPath = String.format(TH_STEP_NAME, stepName);

        int stepIndex = IntStream.range(1, stepElements.size())
                .filter(index -> {
                    WebElement step = stepElements.get(index);
                    try {
                        step.findElement(By.xpath(xPath));
                        return true;
                    } catch (Exception ex) {
                        return false;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst().orElseGet(null);

        WebElement requiredElement = stepElements.get(stepIndex);
        if (Objects.nonNull(requiredElement)) {
            String tdXPath = String.format(CBX_ADD_STEP, ++stepIndex);
            requiredElement.findElement(By.xpath(tdXPath)).click();
        }
    }

    public void clickUseSelectedButton(){
        waitUntilElementIsPresents(BTN_USE_SELECTED).click();
    }

}
