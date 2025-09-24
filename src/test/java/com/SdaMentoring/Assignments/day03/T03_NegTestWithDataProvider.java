package com.SdaMentoring.Assignments.day03;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.TestBase;

public class T03_NegTestWithDataProvider extends TestBase {
/*
Go to https://claruswaysda.github.io/signIn.html
Do negative test with all scenarios
*/
    @DataProvider
    public Object[][] invalidData (){
        return new Object[][]{
                {"",""},//both empty
                {"","admin.123"},//empty username, correct pass
                {"admin",""},//correct username, empty pass
                {"admin",".123"},//correct username, wrong pass
                {"admin*","admin.123"},//wrong username, correct pass
                {"admin*",".123"}// both wrong
        };
    }

    @Test(dataProvider = "invalidData")
        void Test(String username,String password){
        driver.get("https://claruswaysda.github.io/signIn.html");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();


        }
    }
