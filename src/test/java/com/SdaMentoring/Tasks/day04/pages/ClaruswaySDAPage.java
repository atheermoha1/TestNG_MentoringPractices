package com.SdaMentoring.Tasks.day04.pages;

import org.openqa.selenium.By;
import com.SdaMentoring.utilities.Driver;

public class ClaruswaySDAPage {

     By addRecordWebTableBy= By.xpath("//a[.='Add Record Web Table']");

    public AddRecordDataPage clickWebTable(){
        Driver.getDriver().findElement(addRecordWebTableBy).click();
        return new AddRecordDataPage();
    }

}
