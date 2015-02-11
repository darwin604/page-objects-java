package org.vanq.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

// All tests extend BaseTest
public class BaseTest {

    // Get system properties
    protected static final String BASE_URL = System.getProperty("BASE_URL", "http://www.vanq.org/");
    protected static final String BROWSER = System.getProperty("BROWSER", "firefox");
    protected static final boolean REMOTE = Boolean.valueOf(System.getProperty("REMOTE", "false"));
    protected static final String SELENIUM_HOST = System.getProperty("SELENIUM_HOST", "localhost");
    protected static final int SELENIUM_PORT = Integer.valueOf(System.getProperty("SELENIUM_PORT", "4444"));

    protected static RemoteWebDriver driver;

    // Methods annotated with @BeforeClass are run once after all test methods in test class have run
    // This method creates a WebDriver session for the test methods in each test class
    @BeforeClass(alwaysRun = true)
    public void setupWebDriver() throws MalformedURLException {
        if (REMOTE) {
            setupRemoteWebDriver();
            driver.setFileDetector(new LocalFileDetector());
        } else {
            setupLocalWebDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    // Methods annotated with @BeforeMethod are run before each test method in test class
    @BeforeMethod(alwaysRun = true)
    public void loadLandingPage() {
        driver.get(BASE_URL);
    }

    // Methods annotated with @AfterMethod are run after each test method in test class
    @AfterMethod(alwaysRun = true)
    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    // Methods annotated with @AfterClass are run once after all test methods in test class have run
    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }

    // Sets up a Remote WebDriver session, requires Selenium Server to be running on SELENIUM_HOST : SELENIUM_PORT
    private void setupRemoteWebDriver() throws MalformedURLException {
        DesiredCapabilities capabilities;
        switch (BROWSER) {
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                break;
            case "internetExplorer":
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                break;
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                break;
            default:
                throw new RuntimeException("Browser type unsupported:" + BROWSER);
        }
        driver = new RemoteWebDriver(
                new URL("http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub"),
                capabilities);
    }

    // Sets up local WebDriver session, does not require Selenium Server
    private void setupLocalWebDriver() {
        DesiredCapabilities capabilities;
        switch (BROWSER) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "internetExplorer":
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                driver = new InternetExplorerDriver(capabilities);
                break;
            case "chrome":
                String chromeDriverPath = "lib/chromedriver";
                if (System.getProperty("os.name").contains("Windows")) {
                    chromeDriverPath = "lib/chromedriver.exe";
                }
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Browser type unsupported" + BROWSER);
        }
    }

    // Takes a screenshot
    public static void takeScreenshot(String testName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("build/screenshots/"+testName+"_"+BROWSER+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}