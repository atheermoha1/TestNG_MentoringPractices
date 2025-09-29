package com.SdaMentoring.Tasks.day05;

import com.SdaMentoring.Tasks.day05.pages.CLContactListPage;
import com.SdaMentoring.Tasks.day05.pages.CLHomePage;
import com.SdaMentoring.utilities.ConfigReader;
import com.SdaMentoring.utilities.Driver;
import com.SdaMentoring.utilities.DriverSingleton;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class T01_ParallelTestConfiguration {

    /*
    Requirements:
Create a TestNG XML file with parallel execution at the method level
Set thread count to 3
Include at least 2 test classes: PositiveLoginTest and NegativeLoginTest
Configure timeout for each test method to 30 seconds
     */

    @Test(timeOut = 30000)
    void positiveLoginTest() {

        CLHomePage homePage = new CLHomePage();
        CLContactListPage contactListPage = new CLContactListPage();
        Driver.getDriver().get(ConfigReader.getProperty("cl_url"));
        homePage.email.sendKeys(ConfigReader.getProperty("cl_email"));
        homePage.password.sendKeys(ConfigReader.getProperty("cl_password"));
        homePage.submit.click();
        assert contactListPage.logout.isDisplayed();
        Driver.closeDriver();

    }
    @Test(timeOut =30000)
    void negativeloginTest01(){
        CLHomePage homePage = new CLHomePage();
        Driver.getDriver().get(ConfigReader.getProperty("cl_url"));
        homePage.email.sendKeys("wrong_email");
        homePage.password.sendKeys(ConfigReader.getProperty("cl_password"));
        homePage.submit.click();
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(homePage.error));
        assert homePage.error.isDisplayed();
        Driver.closeDriver();
    }
    @Test(timeOut =30000)
    void negativeloginTest02(){
        CLHomePage homePage = new CLHomePage();
        Driver.getDriver().get(ConfigReader.getProperty("cl_url"));
        homePage.email.sendKeys("cl_email");
        homePage.password.sendKeys(ConfigReader.getProperty("cl_password"));
        homePage.submit.click();
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(homePage.error));
        assert homePage.error.isDisplayed();
        Driver.closeDriver();
    }
//    @Test(timeOut =30000)
//    void negativeloginTest03(){
//        CLHomePage homePage = new CLHomePage();
//        Driver.getDriver().get(ConfigReader.getProperty("cl_url"));
//        homePage.email.sendKeys("cl_email");
//        homePage.password.sendKeys(ConfigReader.getProperty("wrong_pass"));
//        homePage.submit.click();
//        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(homePage.error));
//        assert homePage.error.isDisplayed();
//        Driver.closeDriver();
//    }
}
