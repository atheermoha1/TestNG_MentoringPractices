package com.SdaMentoring.Assignments.day04.pages;

import com.SdaMentoring.utilities.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FluentWebTablePage {
    By nameInput= By.id("nameInput");
    By ageInput= By.id("ageInput");
    By countrySelection = By.id("countrySelect");
    By addRecButton = By.xpath("//button[@onclick='addRecord()']");
    By rows= By.xpath("//tbody[@id='tableBody']/tr");


    public FluentWebTablePage enterName(String name){
        DriverSingleton.getDriver().findElement(nameInput).sendKeys(name);
        return this;
    }

    public FluentWebTablePage enterAge(String age){
        DriverSingleton.getDriver().findElement(ageInput).sendKeys(age);
        return this;
    }

    public FluentWebTablePage selectCountryByvisible(int num){
        Select select= new Select(DriverSingleton.getDriver().findElement(countrySelection));
        select.selectByIndex(num);
        return this;
    }
    public FluentWebTablePage addButton(){
        DriverSingleton.getDriver().findElement(addRecButton).click();
        return this;
    }

   public boolean isRecordAdded(String name){
        List<WebElement> allRows= DriverSingleton.getDriver().findElements(rows);
        for (WebElement row:allRows){
            if(row.findElement(By.xpath("./td[1]")).getText().equals(name)){
                return true;
            }
        }
        return false;
   }
}
