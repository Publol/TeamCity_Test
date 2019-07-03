package core.steps.global;

import core.page.CreateProjectFromUrlPage;

public class CreateProductFromUrlStep extends AbstractGlobalStep {

    private String projectName;
    private String buildName;

    public CreateProductFromUrlStep(String projectName, String buildName) {
        super();
        this.projectName = projectName;
        this.buildName = buildName;
    }

    @Override
    void execute() {
        CreateProjectFromUrlPage productFromUrlStep = new CreateProjectFromUrlPage(driver);
        productFromUrlStep.enterProjectName(projectName);
        productFromUrlStep.enterBuildName(buildName);
        productFromUrlStep.clickProceedButton();
    }
}
