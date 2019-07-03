package core.steps.global;

import core.junit.Assertions;
import core.page.CreateProjectPage;
import org.junit.Assert;

import java.util.Objects;

public class CreateProductStep extends AbstractGlobalStep {

    private String rootProject;
    private String repoUrl;
    private String username; //TODO
    private String password; //TODO

    public CreateProductStep(String rootProject, String repoUrl) {
        this(rootProject, repoUrl, null, null);
    }

    public CreateProductStep(String rootProject, String repoUrl, String username, String password) {
        super();
        this.rootProject = rootProject;
        this.repoUrl = repoUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    void execute() {
        CreateProjectPage createProjectPage = new CreateProjectPage(driver);
        createProjectPage.selectParentProject(rootProject);
        createProjectPage.enterRepoUrl(repoUrl);
//        if (Objects.nonNull(username) && Objects.nonNull(password)) {
//            //TODO
//        }
        createProjectPage.clickProceedButton();

        Assertions.assertFalseHard("Error should not presents ", createProjectPage.isErrorPresents());
    }
}
