package utilities;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;


    public ActionHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement findElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element;
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not found within time: " + locator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element not found: " + locator, e);
        } catch (Exception e) {
            throw new RuntimeException("Error finding element: " + locator, e);
        }
    }

    public void click(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();

        } catch (ElementClickInterceptedException e) {

            throw new ElementClickInterceptedException("Element click was intercepted: " + locator, e);
        } catch (Exception e) {

            throw new RuntimeException("Error clicking element: " + locator, e);
        }
    }

    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();

        } catch (ElementClickInterceptedException e) {

            throw new ElementClickInterceptedException("Element click was intercepted", e);
        } catch (Exception e) {

            throw new RuntimeException("Error clicking element", e);
        }
    }

    public void sendKeys(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);

        } catch (Exception e) {

            throw new RuntimeException("Error sending keys to element: " + locator, e);
        }
    }

    public String getText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText();
        } catch (Exception e) {

            throw new RuntimeException("Error getting text from element: " + locator, e);
        }
    }

    public String getText(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.getText();
        } catch (Exception e) {
            throw new RuntimeException("Error getting text from element", e);
        }
    }

    public void acceptAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

        } catch (NoAlertPresentException e) {

            throw new NoAlertPresentException("No alert present to accept", e);
        } catch (Exception e) {

            throw new RuntimeException("Error accepting alert", e);
        }
    }

    public String getAlertText() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return driver.switchTo().alert().getText();
        } catch (NoAlertPresentException e) {

            throw new NoAlertPresentException("No alert present to get text", e);
        } catch (Exception e) {

            throw new RuntimeException("Error getting alert text", e);
        }
    }

    public boolean isElementVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean isElementClickable(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));

            return true;
        } catch (Exception e) {

            return false;
        }
    }
}