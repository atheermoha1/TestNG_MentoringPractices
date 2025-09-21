package com.SdaMentoring.Assignments.day01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class C01_HardAssertLoginTest {
    /*
    Create class HardAssertLoginTest
    Use appropriate WebDriver setup and teardown
    Implement proper element locators
    Use meaningful assertion messages
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
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String currentUrl= driver.getCurrentUrl();

        //use hardAssertions
        Assert.assertEquals(currentUrl,"https://claruswaysda.github.io/signIn.html","new page url is not found ");
        Assert.assertTrue(driver.getPageSource().contains("Employee Table"),"the page does not contain Employee Table");
    }

    @AfterMethod
    void tearDown(){
        driver.quit();
    }
}
