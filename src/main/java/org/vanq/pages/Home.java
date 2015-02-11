package org.vanq.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home extends AbstractBasePage {

    public Home(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getExpectedPageTitle() {
        return "VanQ - Vancouver Testing and Quality Assurance Group";
    }

    // Locators
    By presenterBiosLocator = By.linkText("Presenter Bios");

    // Public methods for page object (this link no loger exists)

    public PresenterBios clickPresenterBios() {
        WebElement presenterBios = driver.findElement(presenterBiosLocator);
        presenterBios.click();
        return new PresenterBios(driver);
    }

}
