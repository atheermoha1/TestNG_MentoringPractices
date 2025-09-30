package com.SdaMentoring.Tasks.day06;

import com.SdaMentoring.Tasks.day06.pages.AddContactPage;
import com.SdaMentoring.Tasks.day06.pages.ContactListPage;
import com.SdaMentoring.Tasks.day06.pages.LoginPage;
import com.SdaMentoring.utilities.CaptureScreenshot;
import com.SdaMentoring.utilities.ConfigReader;
import com.SdaMentoring.utilities.ExtentReportManager;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.SdaMentoring.Tasks.day06.utilities.Driver;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Locale;
public class T01_ContactListExtentReports01 {
    /*
Target: https://thinking-tester-contact-list.herokuapp.com/
Test Scenario:
1. Navigate to the application
2. Create a new user account
3. Login with the created user
4. Add 5 different contacts
5. Assert that all contacts are properly added and displayed
Page Objects Needed:
- LoginPage (registration and login elements)
- ContactListPage (contact management elements)
- AddContactPage (contact form elements)
Assertions:
- Verify successful user registration
- Verify successful login
- Verify each contact is added correctly
- Verify total contact count equals 5
Reporting Requirements:
- Use ExtentReports
- Create TestBaseReport base class
- Add .info() logs for each major step
- Use .pass() for successful assertions
- Use .fail() for failed assertions with screenshots
- Add system information (Browser, Environment, Tester)
- Generate report with timestamp in filename
*/

    @AfterMethod
    void teardown(){
        ExtentReportManager.flushReport();
        Driver.closeDriver();
    }
    @Test
    void getDriverTest(){
        ExtentReportManager.createTest("Create 5 contacts test started");
        LoginPage loginPage = new LoginPage();
        ContactListPage contactListPage = new ContactListPage();
        AddContactPage addContactPage = new AddContactPage();
        Faker faker = new Faker();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

//        Test Scenario:
//        1. Navigate to the application
        try {
            ExtentReportManager.log(Status.PASS,"User Navigate to contatcList page");
            Driver.getDriver().get(ConfigReader.getProperty("cl_url"));

        }catch (Exception e){
            ExtentReportManager.log(Status.FAIL,"User failed to navigate");
        }
//        2. Create a new user account
//        3. Login with the created user
        loginPage.signup.click();
        ExtentReportManager.log(Status.INFO,"user is signing up");
        loginPage.signUp(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password());

        try {
            Assert.assertTrue(contactListPage.logout.isDisplayed()); // Assert successful user registration
            ExtentReportManager.log(Status.PASS,"User created successfully");
        }catch (Exception e){
            ExtentReportManager.log(Status.FAIL,"User is not registered");
        }

//        4. Add 5 different contacts
        for (int i = 0; i<5; i++){
            Driver.getDriver().navigate().refresh();
            contactListPage.addContact.click();
            ExtentReportManager.log(Status.INFO,"Creating contact NO."+i);
            addContactPage.addContact(faker.name().firstName(),
                    faker.name().lastName(),
                    new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(faker.date().birthday()),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().cellPhone().replaceAll("[^0-9]", ""),
                    faker.address().streetAddress(),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().state(),
                    faker.address().zipCode(),
                    faker.country().name());

            // Verify each contact is added correctly
            wait.until(ExpectedConditions.visibilityOf(contactListPage.addContact));
            try {
                Assert.assertTrue(contactListPage.addContact.isDisplayed());
                ExtentReportManager.log(Status.PASS,"User added in contact list");
            }catch (Exception e){
                ExtentReportManager.log(Status.FAIL,"User does not add to contact list");
            }
        }

//        5. Assert that all contacts are properly added and displayed
        int rowCount = contactListPage.dataCount.size();
        if(rowCount==6) {
          //  Assert.assertEquals(rowCount, 6, "You didn't add 5 contacts");
            ExtentReportManager.log(Status.PASS,"all contacts are properly added");
        }else {
            ExtentReportManager.log(Status.FAIL,"all Users do not add to contact list");
            ExtentReportManager.getTest().addScreenCaptureFromPath(CaptureScreenshot.takeScreenshot(Driver.getDriver(),"name"));
        }

    }

}
