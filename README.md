# Appium Testing Framework

A minimalistic, beginner-friendly Appium automation framework built with Java, TestNG, Maven, and Page Object Model (POM) pattern.

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Node.js and npm (for Appium Server)
- Android SDK (for Android testing)
- Xcode (for iOS testing)

## Project Structure

```
appium-framework/
├── src/
│   ├── main/
│   │   ├── java/com/appium/
│   │   │   ├── base/
│   │   │   │   └── DriverManager.java          # Driver initialization and management
│   │   │   └── pages/
│   │   │       ├── BasePage.java               # Base page object with common methods
│   │   │       └── LoginPage.java              # Example page object
│   │   └── resources/
│   │       └── config.properties               # Configuration file
│   └── test/
│       └── java/com/appium/tests/
│           ├── BaseTest.java                   # Base test class with setup/teardown
│           └── LoginTest.java                  # Example test class
├── pom.xml                                     # Maven configuration
├── testng.xml                                  # TestNG configuration
└── README.md                                   # This file
```

## Setup Instructions

### 1. Clone/Create Project
```bash
git clone <repository-url>
cd appium-framework
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Start Appium Server
```bash
appium
```
The server will start on `http://localhost:4723/wd/hub`

### 4. Start Android Emulator (if testing Android)
```bash
emulator -avd <emulator-name>
```

## Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=LoginTest
```

### Run Specific Test Method
```bash
mvn test -Dtest=LoginTest#testSuccessfulLogin
```

## Key Components

### DriverManager (Base)
Handles initialization and management of Appium drivers for both Android and iOS platforms.

**Usage:**
```java
// Initialize Android Driver
DriverManager.initializeAndroidDriver(appPath, appPackage, appActivity);

// Get Driver instance
AppiumDriver driver = DriverManager.getDriver();

// Quit Driver
DriverManager.quitDriver();
```

### BasePage
Base class for all page objects containing common methods like click, sendKeys, getText, etc.

**Methods:**
- `click(By locator)` - Click on element
- `sendKeys(By locator, String text)` - Enter text
- `getText(By locator)` - Get element text
- `isElementDisplayed(By locator)` - Check if element is visible
- `waitForElementToBeVisible(By locator)` - Explicit wait

### Page Objects
Create page objects by extending BasePage. Each page represents a screen in your app.

**Example - HomePage:**
```java
public class HomePage extends BasePage {
    private final By logoutButton = By.id("com.example.app:id/logout");

    public void logout() {
        click(logoutButton);
    }
}
```

### Test Classes
Create test classes by extending BaseTest. TestNG annotations handle setup/teardown.

**Example:**
```java
public class HomeTest extends BaseTest {
    @Test
    public void testUserCanLogout() {
        HomePage homePage = new HomePage();
        homePage.logout();
        // Add assertions
    }
}

```

## Configuration

Update `src/main/resources/config.properties` with your:
- Device names
- App package names and activities
- Platform versions
- Timeout values

Update `src/test/java/com/appium/tests/BaseTest.java` with your actual app details:
```java
protected String appPath = "apps/api-demo.apk";
protected String appPackage = "com.your.app.package";
protected String appActivity = "com.your.app.MainActivity";
```

## Adding New Page Objects

1. Create a new class extending BasePage
2. Define locators as class variables
3. Create methods for user interactions
4. Use inherited BasePage methods for common actions

```java
public class HomePage extends BasePage {
    private final By logoutButton = By.id("com.example.app:id/logout");
    
    public void logout() {
        click(logoutButton);
    }
}
```

## Adding New Tests

1. Create a new test class extending BaseTest
2. Use @Test annotation from TestNG
3. Create page object instances and interact with them
4. Use assertions to verify expected behavior

```java
public class HomeTest extends BaseTest {
    @Test
    public void testUserCanLogout() {
        HomePage homePage = new HomePage();
        homePage.logout();
        // Add assertions
    }
}
```

## Logging

The framework includes logging for debugging. Logs are created at INFO and SEVERE levels for tracking test execution and failures.

## Future Enhancements

- CI/CD integration (Jenkins, GitHub Actions, GitLab CI)
- Parallel test execution
- Test reporting (Extent Reports, Allure)
- Screenshot on failure
- Database integration
- API testing integration

## Troubleshooting

### Appium Server Connection Issues
- Ensure Appium server is running on `localhost:4723`
- Check firewall settings

### Element Not Found
- Verify correct locators in page objects
- Use Appium Inspector to inspect elements
- Adjust wait times in BasePage

### Emulator Not Detected
- Ensure emulator is fully booted
- Run `adb devices` to verify device is connected

## Support

For issues or questions:
1. Check Appium documentation: https://appium.io/docs/en/
2. Review TestNG documentation: https://testng.org/
3. Check element locators using Appium Inspector

## License

This project is open source and available under the MIT License.
