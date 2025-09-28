package com.SdaMentoring.Tasks.day03;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.SdaMentoring.utilities.TestBase;

public class T01_CrossBrowserFormTesting extends TestBase {
    /*
//Go to https://claruswaysda.github.io/ActionsForm.html
//Fill form and submit
//Do all actions and assert
//Do this test with Chrome, Edge and Firefox
//*/

    @Parameters({"browser", "name","age","department","interest","gender"})
    @Test
    void test01(@Optional("chrome") String browser,@Optional("john") String name,@Optional("22") String age,@Optional("IT Department") String department,@Optional("Design") String interest,@Optional("female") String gender) {
        driver.get("https://claruswaysda.github.io/ActionsForm.html");
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("age")).sendKeys(age);

        Select select = new Select(driver.findElement(By.id("options")));
        select.selectByVisibleText(department);

       driver.findElement(By.xpath("//input[@name='pref' and @value='"+interest.toLowerCase()+"']")).click();

        driver.findElement(By.xpath("//input[@name='gender' and @value='" +gender.toLowerCase()+"']")).click();

        driver.findElement(By.xpath("//button[@type='button']")).click();

        //Alert acception
       Alert alert= driver.switchTo().alert();
       String text= alert.getText();
       alert.accept();

       //Assert
        Assert.assertTrue(text.contains("Your passcode is:"));



    }
}

