package core.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminContentPage extends AbstractPage {

    private static final By LYT_ADMIN_CONTENT = By.xpath("//td[contains(@class, 'admin-content')]");

    protected AdminContentPage(WebDriver driver) {
        super(driver, LYT_ADMIN_CONTENT);
    }

    protected WebElement getAdminContent(){
        return waitUntilElementIsPresents(LYT_ADMIN_CONTENT);
    }
}
