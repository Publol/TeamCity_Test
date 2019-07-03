package core.enums;

import org.openqa.selenium.By;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public enum ProjectActionOption {

    COPY_PROJECT,
    MOVE_PROJECT,
    ARCHIVE_PROJECT,
    BULK_EDIT_IDS,
    PAUSE_OR_ACTIVATE,
    SCRABLE_SECURE_VALUE,
    EXPORT_PROJECT,
    DOWNLOAD_SETTINGS_IN_KOTLIN_FORMAT,
    DELETE_PROJECT;

    ProjectActionOption(){

    }

    public By getXpathForAction(){
        switch (this) {
            case DELETE_PROJECT: return By.xpath("//a[contains(@title, 'Delete project')]");
            default: throw new NotImplementedException();

        }
    }

}
