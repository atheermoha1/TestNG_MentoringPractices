package com.SdaMentoring.Assignments.day02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class C01_AmazonSearchTestwithParameters  {
/*
1. Navigate to: https://www.amazon.com
2. Search for different keywords: Java, Selenium
3. Assert that result text contains the searched word
4. Run tests from XML file using parameters
 */
WebDriver driver;
    @BeforeMethod
    void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @Test
    @Parameters("searchKeyword")
    void Test01(String keyword) throws AWTException {
        //Navigate to: https://www.amazon.com
        driver.get("https://www.amazon.com/ref=nav_logo");

        // Search for different keywords: Java, Selenium
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(keyword);
        driver.findElement(By.id("nav-search-submit-button")).click();

        // using robot to press enter but it makes errors
        //        Robot robot= new Robot();
        //        robot.keyPress(KeyEvent.VK_ENTER);
        //        robot.keyRelease(KeyEvent.VK_ENTER);

       // Assert that result text contains the searched word
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='a-color-state a-text-bold']")).getText().replace("\"","").trim().equalsIgnoreCase(keyword),"The result does not contains the keyword");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        //Thread.sleep(3000);
        driver.quit();
    }


}
