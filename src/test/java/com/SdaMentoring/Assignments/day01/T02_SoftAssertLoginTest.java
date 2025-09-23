package com.SdaMentoring.Assignments.day01;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class T02_SoftAssertLoginTest {
    /*
    Create class SoftAssertLoginTest
Handle JavaScript alert using WebDriver's Alert interface
Use SoftAssert object for assertions
Include proper assertAll() call
Add meaningful assertion messages
     */
    WebDriver driver = new ChromeDriver();
    String url ="https://claruswaysda.github.io/signIn.html";
    @BeforeMethod
    void setUp(){
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @Test
    void test01(){
        //locate element and sendKeys
        driver.findElement(By.id("username")).sendKeys("wronguser");
        driver.findElement(By.id("password")).sendKeys("wrongpass");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //softAssertions
        SoftAssert softAssert=new SoftAssert();
        Alert alert =driver.switchTo().alert();
        String alertMsg=  alert.getText();

        softAssert.assertNotNull(alert,"Alert should displayed after incorrect log in");
        softAssert.assertEquals(alertMsg,"Incorrect username or password","Alert message should appear as Incorrect username or password ");
        softAssert.assertAll();

    }

    @AfterMethod
    void tearDown(){
        driver.quit();
    }
}
