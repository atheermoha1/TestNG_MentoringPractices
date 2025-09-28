package com.SdaMentoring.Tasks.day04;

import com.SdaMentoring.Tasks.day04.pages.ActionsFormPage;
import com.SdaMentoring.utilities.ConfigReader;
import com.SdaMentoring.utilities.Driver;
import org.testng.annotations.Test;

public class T02_FormAction {
    /*
Go to
Fill form and submit
Do all actions and assert
*/
    @Test
    void getDriverTest() {
        Driver.getDriver().get(ConfigReader.getProperty("form_url"));

        ActionsFormPage actionsFormPage =new ActionsFormPage();

        actionsFormPage
                .fillForm()
                .department()
                .interest()
                .gender()
                .generatePasscode()
                .SwitchAlert();

        Driver.closeDriver();
    }
}
