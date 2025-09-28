package com.SdaMentoring.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {

    private static WebDriver driver;//Without initialization this is null.

    private Driver() {//None can create an object from this class
    }

    //Safely close the driver
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    //This creates a new WebDriver instance if it does not exist.
    public static WebDriver getDriver() {

        if (driver==null){
            switch (ConfigReader.getProperty("browser").toLowerCase()){
                case "firefox":
                    driver= new FirefoxDriver();
                    break;
                case "edge":
                    driver= new EdgeDriver();
                    break;
                case "headless":
                    driver= new ChromeDriver(new ChromeOptions().addArguments("headless"));
                    break;
                default:
                    driver = new ChromeDriver();
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }



}
