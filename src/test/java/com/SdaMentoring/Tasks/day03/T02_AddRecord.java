package com.SdaMentoring.Tasks.day03;

import com.SdaMentoring.utilities.DataProviderUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.SdaMentoring.utilities.TestBase;

public class T02_AddRecord extends TestBase {
/*
Go to https://claruswaysda.github.io/addRecordWebTable.html
Add records to the table using DataProvider
Do it with all 3 ways
*/

        @DataProvider
        public Object[][] getData01(){
            return new Object[][]{
                    {"John Doe","23","Canada"},
                    {"Mary Lee","34","US"},
                    {"Tom Hanks","82","Australia"},
            };
        }


        @Test(dataProvider = "getData01")
        public void dataProviderTest(String name, String age, String visibleText) {
            driver.get("https://claruswaysda.github.io/addRecordWebTable.html");
            driver.findElement(By.id("nameInput")).sendKeys(name);
            driver.findElement(By.id("ageInput")).sendKeys(age);
            new Select(driver.findElement(By.id("countrySelect"))).selectByVisibleText(visibleText);
            driver.findElement(By.xpath("//*[.='Add Record']")).click();

        }

        @Test(dataProvider = "getData02",dataProviderClass = DataProviderUtilities.class)
        public void dataProviderTest02(String name, String age, String visibleText) {
            driver.get("https://claruswaysda.github.io/addRecordWebTable.html");
            driver.findElement(By.id("nameInput")).sendKeys(name);
            driver.findElement(By.id("ageInput")).sendKeys(age);
            new Select(driver.findElement(By.id("countrySelect"))).selectByVisibleText(visibleText);
            driver.findElement(By.xpath("//*[.='Add Record']")).click();
        }

        @Test(dataProvider = "getData03",dataProviderClass = DataProviderUtilities.class)
        public void dataProviderTest03(String name, double age, String visibleText) {
            driver.get("https://claruswaysda.github.io/addRecordWebTable.html");
            driver.findElement(By.id("nameInput")).sendKeys(name);
            driver.findElement(By.id("ageInput")).sendKeys((int) age + "");
            new Select(driver.findElement(By.id("countrySelect"))).selectByVisibleText(visibleText);
            driver.findElement(By.xpath("//*[.='Add Record']")).click();
        }
    }

