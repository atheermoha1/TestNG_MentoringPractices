package com.SdaMentoring.Assignments.day06;

import com.SdaMentoring.Tasks.day06.pages.AddContactPage;
import com.SdaMentoring.Tasks.day06.pages.ContactListPage;
import com.SdaMentoring.Tasks.day06.pages.LoginPage;
import com.SdaMentoring.Tasks.day06.utilities.Driver;
import com.SdaMentoring.utilities.CaptureScreenshot;
import com.SdaMentoring.utilities.ConfigReader;
import com.SdaMentoring.utilities.ExtentReportManager;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Locale;

public class T01_ContactExtentReports  {
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
- ContactListPage (contact form elements)
Assertions:
- Verify successful user registration
- Verify successful login
- Verify each contact is added correctly
- Verify total contact count equals 5
Reporting Requirements:
- Use ExtentReports
- Add .info() logs for each major step
- Use .pass() for successful assertions
- Use .fail() for failed assertions with screenshots
- Add system information (Browser, Environment, Tester)
- Generate report with timestamp in filename
*/
      LoginPage loginPage;
      ContactListPage contactListPage;
      AddContactPage addContactPage;
      Faker faker;
      WebDriverWait wait;

    @BeforeClass
    void setupReportTest() {
        ExtentReportManager.createTest("Create Five Contacts Test Started");

         loginPage = new LoginPage();
         contactListPage = new ContactListPage();
         addContactPage = new AddContactPage();
         faker = new Faker();
         wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
         //        1. Navigate to the application
            Driver.getDriver().get(ConfigReader.getProperty("cl_url"));
            ExtentReportManager.log(Status.INFO, "User Navigates to Contact list Page");
    }

    @Test
            void Test() {
//        2. Create a new user account
//        3. Login with the created user
        loginPage.signup.click();
        ExtentReportManager.log(Status.INFO, "User is Created and Signed Up");
        loginPage.signUp(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password());

        if (contactListPage.logout.isDisplayed()) {
            ExtentReportManager.log(Status.PASS, "User created successfully");
        } else {
            ExtentReportManager.log(Status.FAIL, "User is not registered");
        }
        Assert.assertTrue(contactListPage.logout.isDisplayed()); // Assert successful user registration


//        4. Add 5 different contacts
        for (int i = 1; i <= 5; i++) {
            int initialCount= contactListPage.dataCount.size();
            wait.until(ExpectedConditions.elementToBeClickable(contactListPage.addContact)).click();
//            Driver.getDriver().navigate().refresh();
//            contactListPage.addContact.click();
            ExtentReportManager.log(Status.INFO, "Creating contact number : " + i);
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
           try {
               wait.until(ExpectedConditions.visibilityOf(contactListPage.addContact));
               ExtentReportManager.log(Status.PASS, "Contact number " + i + " is created successfully");

           }catch (Exception e){
               ExtentReportManager.log(Status.FAIL, "Contact number " + i + " is NOT created ");
               ExtentReportManager.log(Status.INFO,"failure status");
               ExtentReportManager.getTest().addScreenCaptureFromPath(CaptureScreenshot.takeScreenshot(Driver.getDriver(), "name"));

           }
            Assert.assertTrue(contactListPage.addContact.isDisplayed());
        }

//        5. Assert that all contacts are properly added and displayed
        int rowCount = contactListPage.dataCount.size();
        if (rowCount == 6) {
            ExtentReportManager.log(Status.PASS, "Five contacts are added successfully");
        }
        else {
            ExtentReportManager.log(Status.FAIL, "Some contact are not added successfully");
            ExtentReportManager.log(Status.INFO,"failure status");

            try {
                wait.until(ExpectedConditions.visibilityOf(contactListPage.dataCount.get(contactListPage.dataCount.size()-1)));
            }catch (Exception e){

            }
            String screenShot= CaptureScreenshot.takeScreenshot(Driver.getDriver(),"Contact list pic");
            ExtentReportManager.getTest().addScreenCaptureFromPath(screenShot, "name");

        }
        Assert.assertEquals(rowCount, 6);
    }

    @AfterClass
    void tearDown(){
        Driver.closeDriver();
        ExtentReportManager.flushReport();
    }
    }
