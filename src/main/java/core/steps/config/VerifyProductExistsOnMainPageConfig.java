package core.steps.config;

import core.enums.SearchProjectType;

public class VerifyProductExistsOnMainPageConfig implements AbstractStepConfig{

    private String projectName;
    private SearchProjectType projectType;
    private int projectIndex;

    public VerifyProductExistsOnMainPageConfig setProjectName(String projectName){
        this.projectName = projectName;
        this.projectType = SearchProjectType.NAME;
        return this;
    }

    public VerifyProductExistsOnMainPageConfig setProjectIndex(int index) {
        this.projectIndex = index;
        this.projectType = SearchProjectType.INDEX;
        return this;
    }

    public int getProjectIndex() {
        return projectIndex;
    }

    public String getProjectName() {
        return projectName;
    }

    public SearchProjectType getProjectType() {
        return projectType;
    }

    @Override
    public AbstractStepConfig build() {
        return this;
    }
}
