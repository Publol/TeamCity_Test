package core.steps;

import core.enums.ProjectActionOption;
import core.hooks.CucumberHooks;
import core.page.MainPage;
import core.page.ProjectGeneralSettings;
import core.page.ProjectOverviewPage;
import core.selenium.SelCoreUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Objects;

public class DeleteProductStepsUI extends AbstractStep {

    public DeleteProductStepsUI(CucumberHooks cucumberHooks) {
        super(cucumberHooks);
    }



    @And("^User should find \"([^\"]*)\" existing project$")
    public void userShouldFindProject_NameExistingProject(String projectName) {
        MainPage mainPage = new MainPage(driver);
        MainPage.ProjectRow projectRowBy = mainPage.getProjectRowBy(projectName);

        Assert.assertTrue("Project is exist", Objects.nonNull(projectRowBy));

        projectRowBy.clickToProjectName();
    }


    @Then("^User should have possibility to delete project$")
    public void userShouldHavePossibilityToDeleteProject() {
        ProjectOverviewPage projectOverviewPage = new ProjectOverviewPage(driver);
        ProjectGeneralSettings projectGeneralSettings = projectOverviewPage.clickToEditProjectSettings();
        projectGeneralSettings.clickActionsAndSelect(ProjectActionOption.DELETE_PROJECT);

        Assert.assertTrue("Alert message is presented", projectGeneralSettings.isAlertPresented());

        projectGeneralSettings.acceptDeleteAlert();
    }

    @And("^Project \"([^\"]*)\" should be successfully deleted$")
    public void projectShouldBeSuccessfullyDeleted(String projectName) {
        MainPage mainPage = MainPage.navigateToMainPage(driver);

        MainPage.ProjectRow projectRowBy = mainPage.getProjectRowBy(projectName);

        Assert.assertTrue("Project is no more exist", Objects.isNull(projectRowBy));
    }

}
