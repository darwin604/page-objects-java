package org.vanq.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.vanq.pages.Home;

public class LogoTest extends BaseTest {

    @Test(groups = {"functional"})
    public void vanqLogoDisplayedOnHomePageTest() {
        Home home = new Home(driver);
        Assert.assertTrue(home.isVanqLogoDisplayed());
    }

}
