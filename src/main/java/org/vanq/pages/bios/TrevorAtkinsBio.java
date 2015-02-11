package org.vanq.pages.bios;

import org.openqa.selenium.WebDriver;

public class TrevorAtkinsBio extends AbstractBioPage {

    public TrevorAtkinsBio(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getExpectedPageTitle() {
        return "Trevor Atkins";
    }

}
