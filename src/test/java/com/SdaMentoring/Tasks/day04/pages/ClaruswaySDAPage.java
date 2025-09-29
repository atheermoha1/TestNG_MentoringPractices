package com.SdaMentoring.Tasks.day04.pages;

import org.openqa.selenium.By;
import com.SdaMentoring.utilities.DriverSingleton;

public class ClaruswaySDAPage {

     By addRecordWebTableBy= By.xpath("//a[.='Add Record Web Table']");

    public AddRecordDataPage clickWebTable(){
        DriverSingleton.getDriver().findElement(addRecordWebTableBy).click();
        return new AddRecordDataPage();
    }

}
