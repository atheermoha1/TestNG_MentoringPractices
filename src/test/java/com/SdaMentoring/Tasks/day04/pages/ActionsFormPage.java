package com.SdaMentoring.Tasks.day04.pages;

import com.SdaMentoring.utilities.DriverSingleton;
import com.github.javafaker.Faker;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ActionsFormPage {

    By nameInput= By.id("name");
    By ageInput= By.id("age");
    By departement=By.id("options");
    By interest=By.xpath("//input[@value='management']");
    By gender=By.xpath("//input[@value='female']");
    By clickButton=By.xpath("//button[@onclick='generatePasscode()']");
    WebDriverWait wait =new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(10));


    public ActionsFormPage enterName(String name){
        DriverSingleton.getDriver().findElement(nameInput).sendKeys(name);
        return this;
    }

    public ActionsFormPage enterAge(String age){
        DriverSingleton.getDriver().findElement(ageInput).sendKeys(age);
        return this;
    }

    public ActionsFormPage department(){
        DriverSingleton.getDriver().findElement(departement).click();
        Select select =new Select(DriverSingleton.getDriver().findElement(departement));
        select.selectByIndex(2);
        return this;
    }

    public ActionsFormPage interest(){
        DriverSingleton.getDriver().findElement(interest).click();
        return this;
    }

    public ActionsFormPage gender(){
        DriverSingleton.getDriver().findElement(gender).click();
        return this;
    }

    public ActionsFormPage generatePasscode(){
        DriverSingleton.getDriver().findElement(clickButton).click();
        return this;
    }

    public ActionsFormPage fillForm(){
        Faker faker =new Faker();
        enterName(faker.name().fullName());
        enterAge(faker.number().numberBetween(18,35)+"");
        return this;
        }

        public ActionsFormPage SwitchAlert() {
            //first alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMsg1 = alert.getText();
            Assert.assertTrue(alertMsg1.contains("Your passcode is:"));
            // extract the num
            String passcode = alertMsg1.replaceAll("\\D+", "");
            alert.accept();

            //second alert
            Alert alert2 = wait.until(ExpectedConditions.alertIsPresent());
            String alertMsg2 =alert2.getText();
            Assert.assertTrue(alertMsg2.contains("Please enter the"));
            alert2.sendKeys(passcode);
            alert2.accept();

            return this;
        }
}
