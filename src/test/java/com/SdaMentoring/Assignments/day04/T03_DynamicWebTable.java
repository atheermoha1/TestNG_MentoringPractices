package com.SdaMentoring.Assignments.day04;


import com.SdaMentoring.Assignments.day04.pages.FluentWebTablePage;
import com.SdaMentoring.utilities.ConfigReader;
import com.SdaMentoring.utilities.DriverSingleton;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class T03_DynamicWebTable {


  @DataProvider
    public Object[][] addRecords(){
        return new Object[][]{
                {"John", "20",1},
                {"Michale","25",4},
                {"Heaven","19",3},
                {"Macdonald","23",5},
                {"Tony","28",2}
        };
    }

    @Test(dataProvider="addRecords")
    void getDriverTest(String name, String age, int countryIndex) {

        DriverSingleton.getDriver().get(ConfigReader.getProperty("addRecord_url2"));
        FluentWebTablePage fluentWebTablePage = new FluentWebTablePage();


        fluentWebTablePage
                         .enterName(name)
                         .enterAge(age)
                         .selectCountryByvisible(countryIndex)
                         .addButton();

         Assert.assertTrue(fluentWebTablePage.isRecordAdded(name));


            DriverSingleton.closeDriver();
        }
    }

