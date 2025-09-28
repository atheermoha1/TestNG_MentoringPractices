package com.SdaMentoring.Tasks.day01;

import org.testng.annotations.Test;
import com.SdaMentoring.utilities.TestBase;

public class T03_DependencyTest extends TestBase{
    /*
    Task 3: Dependency Testing
Objective: Create interdependent test methods using dependsOnMethods
Requirements:
Create a class called DependencyTest
Create a @BeforeClass method to set up WebDriver
Create the following dependent test chain:
openYahoo() - Navigate to Yahoo
openBing() - Navigate to Bing (depends on Yahoo test)
openDuckDuckGo() - Navigate to DuckDuckGo (depends on Bing test)
Add intentional failure in Yahoo test and observe behavior
Create @AfterClass method to close driver
     */

    @Test
    void google() {
        driver.get("https://www.google.com/");
        //Assert.fail();
    }

    @Test(dependsOnMethods = "google")
    void Bing() {
        driver.get("https://www.bing.com/");
    }

    @Test(dependsOnMethods = {"Bing","google"})
    void DuckDuckGo() {
        driver.get("https://duckduckgo.com/");
    }

}
