package com.SdaMentoring.Assignments.day03;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.SdaMentoring.utilities.TestBase;

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

        try {
            //assert that alert appears for incorrect input
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            Assert.assertEquals(text, "Incorrect username or password");
            alert.accept();
        }catch(NoAlertPresentException e) {
            //assert the empty fields
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement usernameField = driver.findElement(By.id("username"));
            String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", usernameField);
            WebElement passField = driver.findElement(By.id("password"));
            String validationMessage2 = (String) js.executeScript("return arguments[0].validationMessage;", passField);
            //I tried to handle it with .getText() but it didn't work,then after I search > getAttribute("value") read what is typed
            if(usernameField.getAttribute("value").isEmpty()){
                System.out.println("validationMessage = " + validationMessage);
                Assert.assertEquals(validationMessage, "Please fill out this field.");
            }else{
                Assert.assertEquals(validationMessage2, "Please fill out this field.");

            }
        }
}
    }
