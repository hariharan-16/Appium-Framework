package com.appium.tests;

import com.appium.base.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.logging.Logger;

public class BaseTest {

    protected AppiumDriver driver;

    protected String appPath = System.getProperty("user.dir") + "/apps/api-demo.apk";
    protected String appPackage = "io.appium.android.apis";
    protected String appActivity = "io.appium.android.apis.ApiDemos";
    private static final Logger logger = Logger.getLogger(BaseTest.class.getName());

    /**
     * Setup - Initialize driver before each test
     */
    @BeforeMethod
    public void setup() {
        logger.info("Setting up test...");
        driver = DriverManager.initializeAndroidDriver(
                appPath, appPackage, appActivity
        );
    }

    /**
     * Teardown - Quit driver after each test
     */
    @AfterMethod
    public void teardown() {
        logger.info("Tearing down test...");
        DriverManager.quitDriver();
    }
}
