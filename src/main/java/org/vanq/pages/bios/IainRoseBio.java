package org.vanq.pages.bios;

import org.openqa.selenium.WebDriver;

public class IainRoseBio extends AbstractBioPage {

    public IainRoseBio(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getExpectedPageTitle() {
        return "Iain Rose";
    }


}
