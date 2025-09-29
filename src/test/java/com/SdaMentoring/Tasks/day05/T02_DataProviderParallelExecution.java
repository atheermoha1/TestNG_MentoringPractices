package com.SdaMentoring.Tasks.day05;

import com.SdaMentoring.utilities.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class T02_DataProviderParallelExecution {
    @Test(dataProvider = "DataProvider")
    public void searchWords(String searchWord ) {
        //navigate to
        //https://www.amazon.sa
        Driver.getDriver().get("https://www.amazon.sa");
        // write in search box
        Driver.getDriver().findElement(By.id("twotabsearchtextbox")).sendKeys(searchWord);
        Driver.getDriver().findElement(By.id("nav-search-submit-button")).click();

        //assert
        Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//span[@class='a-color-state a-text-bold']")).getText().replace("\"","").trim().equalsIgnoreCase(searchWord),"The result does not contains the keyword");

    }

    // By default, parallel = false. To enable parallel execution, specify parallel = true
    @DataProvider(parallel = true)
    public Object[][] DataProvider() {
        Object search[][] = {
                {"tote bag"},
                {"coffee mug"},
                {"novel book"},
                {"eye glasses"},
                {"ipad air"}
        };
        return search;
    }
}
