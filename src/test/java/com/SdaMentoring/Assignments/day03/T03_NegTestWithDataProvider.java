package com.SdaMentoring.Assignments.day03;

import org.openqa.selenium.*;
import org.testng.Assert;
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
                {"admin",""},//correct username , empty pass
                {"admin",".123"},//correct username, wrong pass
                {"admin*","admin.123"},//wrong username, correct pass
                {"admin*",".123"}// both wrong
        };
    }

    @Test(dataProvider = "invalidData")
        void Test(String username,String password) throws InterruptedException {

        driver.get("https://claruswaysda.github.io/signIn.html");
        // pass input using data provider
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(2000);

        //assert the empty fields>>> I faced errors on it and I do not know how to handle it
      //  JavascriptExecutor js = (JavascriptExecutor) driver;
//        WebElement usernameField = driver.findElement(By.id("username"));
//        String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", usernameField);
//        System.out.println("validationMessage = " + validationMessage);
//        Assert.assertEquals(validationMessage, "Please fill out this field.");
//

        //assert that alert appears for incorrect input accept the alert
        Alert alert= driver.switchTo().alert();
        String text=alert.getText();
        Assert.assertEquals(text,"Incorrect username or password");
        alert.accept();

}
    }
