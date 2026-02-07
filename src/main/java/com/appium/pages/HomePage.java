package com.appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"API Demos\")")
    private WebElement homePageTitle;

    public WebElement getHomePageTitle() {
        return homePageTitle;
    }
}
