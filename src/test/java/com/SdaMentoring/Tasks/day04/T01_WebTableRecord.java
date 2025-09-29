package com.SdaMentoring.Tasks.day04;

import com.SdaMentoring.Tasks.day04.pages.ClaruswaySDAPage;
import org.testng.annotations.Test;
import com.SdaMentoring.utilities.ConfigReader;
import com.SdaMentoring.utilities.DriverSingleton;

public class T01_WebTableRecord {
    /*
Go to https://claruswaysda.github.io/
Add 5 records
Delete first record you created.
(Use Fluent POM and don't use any index in locators)
*/

    @Test
    void getDriverTest(){
        DriverSingleton.getDriver().get(ConfigReader.getProperty("addRecord_url"));
        ClaruswaySDAPage claruswaySDAPage =new ClaruswaySDAPage();
        claruswaySDAPage
                .clickWebTable()
                .enterFakerRecord(5)
                .deleteLatAddedRecord();

DriverSingleton.closeDriver();
    }
}
