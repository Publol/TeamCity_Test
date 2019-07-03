package core.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdministrationPage extends AbstractPage {

    private static final By LYT_ADMIN_BAR =     By.xpath("//td[contains(@class, 'admin-sidebar')]");
    private static final By ITM_PROJECTS =      By.xpath("//div[contains(@class, 'item active')]/a[contains(text(), 'Projects')]");
    protected AdministrationPage(WebDriver driver) {
        super(driver, LYT_ADMIN_BAR);
    }

    private WebElement getAdminBar(){
        return waitUntilElementIsPresents(LYT_ADMIN_BAR);
    }

    public ProjectItemPage navigateToProjectsItem(){
        waitUntilElementIsPresents(getAdminBar(),ITM_PROJECTS).click();
        return new ProjectItemPage(driver);
    }


}
