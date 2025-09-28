package com.SdaMentoring.Tasks.day03;

import com.SdaMentoring.utilities.DataProviderUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.time.Duration;

public class T02AdditionalTask {

    WebDriver driver;
    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://claruswaysda.github.io/addRecordWebTable.html");

    }
    @Test(dataProvider = "getData03",dataProviderClass = DataProviderUtilities.class)
    public void dataProviderTest03(String name, double age, String visibleText) {
        driver.findElement(By.id("nameInput")).sendKeys(name);
        driver.findElement(By.id("ageInput")).sendKeys((int) age + "");
        new Select(driver.findElement(By.id("countrySelect"))).selectByVisibleText(visibleText);
        driver.findElement(By.xpath("//*[.='Add Record']")).click();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
