package org.vanq.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.vanq.AbstractPageObject;

public abstract class AbstractBasePage extends AbstractPageObject {

    public AbstractBasePage(WebDriver driver) {
        super(driver);
    }

    // Locators for WebElements common to all Vanq.org pages

    By homeTabLocator = By.cssSelector("li#menu-item-64 > a");
    By meetingsTabLocator = By.cssSelector("li#menu-item-63 > a");
    By missionTabLocator = By.cssSelector("li#menu-item-60 > a");
    By sponsorTabLocator = By.cssSelector("li#menu-item-61 > a");
    By vanqLogo = By.cssSelector("img[alt='VanQ Logo']");

    // Public methods common to all Vanq.org pages

    // Click the home tab in the nav bar
    public Home clickHomeTab() {
        WebElement homeTab = driver.findElement(homeTabLocator);
        homeTab.click();
        return new Home(driver);
    }

    // Click the meetings tab in the nav bar
    public Meetings clickMeetingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(meetingsTabLocator));
        WebElement meetingsTab = driver.findElement(meetingsTabLocator);
        meetingsTab.click();
        return new Meetings(driver);
    }

    // Click the mission tab in the nav bar
    public Mission clickMissionTab() {
        wait.until(ExpectedConditions.elementToBeClickable(missionTabLocator));
        WebElement missionTab = driver.findElement(missionTabLocator);
        missionTab.click();
        return new Mission(driver);
    }

    // Click the sponsors tab in the nav bar
    public Sponsors clickSponsorsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(sponsorTabLocator));
        WebElement sponsorTab = driver.findElement(sponsorTabLocator);
        sponsorTab.click();
        return new Sponsors(driver);
    }

    // Checks if VanQ logo is displayed
    public boolean isVanqLogoDisplayed() {
        return driver.findElements(vanqLogo).size() > 0;
    }

}
