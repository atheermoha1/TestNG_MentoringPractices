
package com.SdaMentoring.Tasks.day05.pages;

import com.SdaMentoring.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.SdaMentoring.utilities.DriverSingleton;

public class CLContactListPage {

    public CLContactListPage(){

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "logout")
    public WebElement logout;

}
