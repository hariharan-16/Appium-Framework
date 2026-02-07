package com.appium.tests;

import com.appium.pages.HomePage;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest{

    @Test
    public void Test01(){
        // Verify Home Page Title
        HomePage hp = new HomePage(driver);
        assert hp.getHomePageTitle().isDisplayed() : "Home Page Title is not displayed";
    }

}
