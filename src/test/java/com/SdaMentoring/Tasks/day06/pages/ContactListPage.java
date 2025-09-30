package com.SdaMentoring.Tasks.day06.pages;

import com.SdaMentoring.Tasks.day06.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ContactListPage {

    public ContactListPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "add-contact")
    public WebElement addContact;

    @FindBy(id = "logout")
    public WebElement logout;

    @FindBy(xpath = "//td[2]")
    public List<WebElement> dataCount;

    @FindBy(xpath = "//table//tr//td[4]")
    public List<WebElement> emailColumn;

}
