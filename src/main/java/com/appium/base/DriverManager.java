package com.appium.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.logging.Logger;

public class DriverManager {

    private static AppiumDriver driver;
    private static final Logger logger = Logger.getLogger(DriverManager.class.getName());

    /**
     * Initialize Android driver
     */
    public static AppiumDriver initializeAndroidDriver(String appPath, String appPackage, String appActivity) {
        try {
            UiAutomator2Options options = new UiAutomator2Options();

            options.setDeviceName("emulator-5554");
            options.setApp(appPath);
            options.setAppPackage(appPackage);
            options.setAppActivity(appActivity);
            options.setAutomationName("UiAutomator2");
            options.setPlatformName("Android");

            driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723"),
                    options
            );

            logger.info("Android driver initialized successfully");
            return driver;
        } catch (Exception e) {
            logger.severe("Failed to initialize Android driver: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Initialize iOS driver
     */
    public static AppiumDriver initializeIOSDriver(String appPath) {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "iOS");
            caps.setCapability("deviceName", "iPhone 14");
            caps.setCapability("automationName", "XCUITest");
            caps.setCapability("app", appPath);

            driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
            logger.info("iOS driver initialized successfully");
            return driver;
        } catch (Exception e) {
            logger.severe("Failed to initialize iOS driver: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Get current driver instance
     */
    public static AppiumDriver getDriver() {
        if (driver == null) {
            throw new RuntimeException("Driver not initialized. Call initializeAndroidDriver() or initializeIOSDriver() first.");
        }
        return driver;
    }

    /**
     * Quit driver
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Driver quit successfully");
        }
    }
}
