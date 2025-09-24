package com.SdaMentoring.Assignments.day03;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.TestBase;

public class newTask extends TestBase {

    @DataProvider
    public Object[][] invalidData() {
        return new Object[][]{
                {"", ""},            // both empty
                {"", "admin.123"},   // empty username
                {"admin", ""},       // empty password
                {"admin", ".123"},   // correct username, wrong password
                {"admin*", "admin.123"}, // wrong username, correct password
                {"admin*", ".123"}   // both wrong
        };
    }

    @Test(dataProvider = "invalidData")
    void Test(String username, String password) throws InterruptedException {

        driver.get("https://claruswaysda.github.io/signIn.html");

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);

        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(1000);

        // Try alert first
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            Assert.assertEquals(alertText, "Incorrect username or password");
            alert.accept();
            return; // stop here if alert handled
        } catch (NoAlertPresentException e) {
            // no alert → check HTML5 validation
        }

        // If no alert, check validation messages
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String usernameMessage = (String) js.executeScript("return arguments[0].validationMessage;", usernameInput);
        String passwordMessage = (String) js.executeScript("return arguments[0].validationMessage;", passwordInput);

        if (!usernameMessage.isEmpty()) {
            System.out.println("Username validation: " + usernameMessage);
            Assert.assertEquals(usernameMessage, "Please fill out this field.");
        }

        if (!passwordMessage.isEmpty()) {
            System.out.println("Password validation: " + passwordMessage);
            Assert.assertEquals(passwordMessage, "Please fill out this field.");
        }
    }

}
