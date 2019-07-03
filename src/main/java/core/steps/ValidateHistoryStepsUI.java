package core.steps;

import core.hooks.CucumberHooks;
import core.junit.Assertions;
import core.page.BuildAdditionalInformation;
import core.page.BuildHistory;
import core.page.MainPage;

import core.page.ProjectBuildStepsPage;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

public class ValidateHistoryStepsUI extends AbstractStep {

    public ValidateHistoryStepsUI(CucumberHooks cucumberHooks) {
        super(cucumberHooks);
    }


    @When("^User click run build on \"([^\"]*)\" project and wait until build \"([^\"]*)\" will be completed in (-?\\d+) seconds$")
    public void userClickRunBuildOnProject_NameProjectAndWaitUntilBuildBuild_NameWillBeCompletedInTimeoutSeconds(String projectName, String buildName, int timeout) {
        MainPage mainPage = new MainPage(driver);
        MainPage.ProjectRow projectRow = mainPage.getProjectRowBy(projectName);
        projectRow.expandProject();
        projectRow.runBuildByName(buildName, true, timeout);
    }

    @And("^Select \"([^\"]*)\" build step$")
    public void selectBuildStep(String buildStep) throws Throwable {
        ProjectBuildStepsPage projectBuildStepsPage = new ProjectBuildStepsPage(driver);
        projectBuildStepsPage.checkStepBy(buildStep);
        projectBuildStepsPage.clickUseSelectedButton();
    }

    @Then("^Build history for \"([^\"]*)\" project and \"([^\"]*)\" build should display build current build history$")
    public void buildHistoryForProject_NameProjectAndBuild_NameBuildShouldDisplayBuildCurrentBuildHistory(String projectName, String buildName) {
        MainPage mainPage = new MainPage(driver);
        MainPage.ProjectRow projectRow = mainPage.getProjectRowBy(projectName);
        BuildAdditionalInformation build = projectRow.clickOnBuildBy(buildName);
        BuildHistory buildHistoryTab = build.clickByBuildHistoryTab();
        Assertions.assertTrue("Build history is successfully displayed", buildHistoryTab.getRowBy(1).getStatus().contains("Tests passed"));

        MainPage.navigateToMainPage(driver);
    }

}
