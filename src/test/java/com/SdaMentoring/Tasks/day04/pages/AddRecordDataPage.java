package com.SdaMentoring.Tasks.day04.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.SdaMentoring.utilities.Driver;

import java.util.List;

public class AddRecordDataPage {

    By nameInput= By.id("nameInput");
    By ageInput= By.id("ageInput");
    By countrySelection = By.id("countrySelect");
    By addRecButton = By.xpath("//button[@onclick='addRecord()']");
    By addedRecordsBy = By.xpath("//tbody/tr");


    public AddRecordDataPage enterName(String name){
        Driver.getDriver().findElement(nameInput).sendKeys(name);
        return this;
    }

    public AddRecordDataPage enterAge(String age){
        Driver.getDriver().findElement(ageInput).sendKeys(age);
        return this;
    }

    public AddRecordDataPage selectCountryByvisible(int num){
        Select select= new Select(Driver.getDriver().findElement(countrySelection));
        select.selectByIndex(num);
        return this;
    }
    public AddRecordDataPage addButton(){
        Driver.getDriver().findElement(addRecButton).click();
        return this;
    }

    public AddRecordDataPage enterFakerRecord(int num){
        Faker faker =new Faker();
        for(int i=0;i<num;i++){
            enterName(faker.name().fullName());
            enterAge(faker.number().numberBetween(18,50)+"");
            selectCountryByvisible(faker.number().numberBetween(1,5));
            addButton();
        }
        return this;
    }

    public AddRecordDataPage deleteLatAddedRecord(){
        List<WebElement> recordList =Driver.getDriver().findElements(addRecButton);
        recordList.getLast().findElement(By.xpath("//button[.='Delete']"));
        return this;
    }


}
