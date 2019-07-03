package core.steps;

import core.env.Environment;
import core.hooks.CucumberHooks;
import core.page.AdministrationPage;
import core.page.MainPage;
import core.page.ProjectItemPage;
import core.steps.config.VerifyProductExistsOnMainPageConfig;
import core.steps.global.CreateProductFromUrlStep;
import core.steps.global.CreateProductStep;
import core.steps.global.LogInStep;
import core.steps.global.VerifyProductExistOnMainPageSteps;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

public class CreateProductStepsUI extends AbstractStep{

    public CreateProductStepsUI(CucumberHooks hooks) {
        super(hooks);
    }

    @When("^User create new project with \"([^\"]*)\" project name and \"([^\"]*)\" build name$")
    public void userCreateNewProjectWithProject_NameProjectNameAndBuild_NameBuildName(String projectName, String buildName) {
        String rootName = Environment.getProperty("project.root.name");
        String repoUrl = Environment.getProperty("project.git.url");
        steps
                .addStep(new CreateProductStep(rootName, repoUrl))
                .addStep(new CreateProductFromUrlStep(projectName, buildName))
                .execute();
    }

    @Given("^When User open create project menu$")
    public void whenUserOpenCreateProjectMenu() {
        MainPage mainPage = new MainPage(driver);

        AdministrationPage administrationPage = mainPage.navigateToAdministrationPage();
        ProjectItemPage projectItemPage = administrationPage.navigateToProjectsItem();
        projectItemPage.clickCreateProjectButton();
    }



    @Then("^Project with \"([^\"]*)\" should be displayed on main page$")
    public void projectWithProject_NameShouldBeDisplayedOnMainPage(String projectName) {
        VerifyProductExistsOnMainPageConfig config = new VerifyProductExistsOnMainPageConfig();
        config
                .setProjectName(projectName);

        steps
                .addStep(new VerifyProductExistOnMainPageSteps(config))
                .execute();
    }





}
