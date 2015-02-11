package org.vanq.pages;

import org.openqa.selenium.WebDriver;

public class Sponsors extends AbstractBasePage {

    public Sponsors(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getExpectedPageTitle() {
        return "VANQ Sponsors";
    }
}
