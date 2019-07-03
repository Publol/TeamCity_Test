package core.steps.global;

import core.enums.SearchProjectType;
import core.page.MainPage;
import core.steps.config.AbstractStepConfig;
import core.steps.config.VerifyProductExistsOnMainPageConfig;
import org.apache.log4j.Logger;

import java.util.Objects;

public class VerifyProductExistOnMainPageSteps extends AbstractGlobalStep implements StepWithConfig{
    private final Logger logger = Logger.getLogger(VerifyProductExistOnMainPageSteps.class);

    public VerifyProductExistOnMainPageSteps(VerifyProductExistsOnMainPageConfig stepConfig) {
        initConfig(stepConfig);
    }

    @Override
    void execute() {
        VerifyProductExistsOnMainPageConfig config = getConfig();

        MainPage mainPage = MainPage.navigateToMainPage(driver);
        MainPage.ProjectRow projectRowBy;

        if (config.getProjectType().equals(SearchProjectType.INDEX)) {
            projectRowBy = mainPage.getProjectRowBy(config.getProjectIndex());
        } else {
            projectRowBy = mainPage.getProjectRowBy(config.getProjectName());
        }

        if (Objects.isNull(projectRowBy)) {
            logger.error("Project was not found");
            assert false;
        }
    }

    @Override
    public void initConfig(AbstractStepConfig config) {
        abstractStepConfig = config;
    }

    @Override
    public VerifyProductExistsOnMainPageConfig getConfig() {
        return (VerifyProductExistsOnMainPageConfig) this.abstractStepConfig;
    }
}
