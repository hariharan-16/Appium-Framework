package com.appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.appium.base.DriverManager;
import java.time.Duration;
import java.util.logging.Logger;

public class BasePage {

    protected AppiumDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(BasePage.class.getName());
    private static final long TIMEOUT = 10;

    /**
     * Constructor - Initialize driver and wait
     */
    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),
                this
        );
    }

    /**
     * Click on element
     */
    public void click(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked on element: " + locator);
        } catch (Exception e) {
            logger.severe("Failed to click on element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Send keys to element
     */
    public void sendKeys(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
            logger.info("Sent keys to element: " + locator);
        } catch (Exception e) {
            logger.severe("Failed to send keys to element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get text from element
     */
    public String getText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.info("Retrieved text from element: " + locator);
            return element.getText();
        } catch (Exception e) {
            logger.severe("Failed to get text from element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Check if element is displayed
     */
    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            logger.info("Element not displayed: " + locator);
            return false;
        }
    }

    /**
     * Wait for element to be visible
     */
    public void waitForElementToBeVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element is visible: " + locator);
        } catch (Exception e) {
            logger.severe("Element not visible: " + locator + " - " + e.getMessage());
            throw e;
        }
    }
}
