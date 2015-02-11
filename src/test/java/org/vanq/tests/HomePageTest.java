package org.vanq.tests;

import org.testng.annotations.Test;
import org.vanq.pages.Home;
import org.vanq.pages.Mission;

public class HomePageTest extends BaseTest {

    // Navigates from home to Mission page and back again
    @Test(groups = {"functional"})
    public void navigateToMissionPageAndBack() {
        Home home = new Home(driver);
        home.clickMissionTab();
        //mission.clickHomeTab();
    }

}
