package org.vanq.pages;

import org.openqa.selenium.WebDriver;

public class Mission extends AbstractBasePage {

    public Mission(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getExpectedPageTitle() {
        return "VanQ's Mission Statement";
    }

}
