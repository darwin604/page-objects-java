package org.vanq;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

// All page objects extend AbstractPageObject
public abstract class AbstractPageObject {

    // Handles to WebDriver that each page object will inherit
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Constructor for each page object
    public AbstractPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = (new WebDriverWait(driver, 60));
        waitForPageToLoad();
    }

    // Each page object will implement this method to return its expected page name.
    public abstract String getExpectedPageTitle();

    // Wait until the page name of the current page matches our expected value else fail the test if the wait times out.
    public void waitForPageToLoad() {
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver d) {
                    String actualPageTitle = driver.getTitle();
                    return actualPageTitle.equals(getExpectedPageTitle());
                }
            });
        } catch (TimeoutException e) {
            Assert.fail("Timed out loading " + this.getClass().getSimpleName() + " page");
        }
    }
}