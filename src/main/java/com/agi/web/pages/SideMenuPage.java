package com.agi.web.pages;

import com.agi.web.objects.SideMenuPageObjects;
import com.alghurair.base.WebBasePage;
import com.alghurair.manager.ReportManager;
import com.alghurair.web.Browser;
import org.testng.Assert;

public class SideMenuPage extends WebBasePage {
    public static final ReportManager logger = new ReportManager(SideMenuPage.class);

    /**
     * Page action
     * A Page action is the granular level of action performed on that page.
     */
    public void clickMenu(String menu) throws Exception {
        logger.info("Click Menu - " + menu);
        Browser.waitUntilVisibilityOfElement(SideMenuPageObjects.getMenu(menu), elementTimeout);
        Browser.click(SideMenuPageObjects.getMenu(menu));
    }

    /**
     * Page function
     * A Page function is the group of action.
     */
    public void openMenuPage(String menu) throws Exception {
        clickMenu(menu);
        waitForMenuActive(menu);
    }

    /**
     * Wait function
     * A Wait function is the one which waits for certain event on that page.
     */
    public void waitForMenu(String menu) throws Exception {
        Browser.waitUntilVisibilityOfElement(SideMenuPageObjects.getMenu(menu), elementTimeout);
    }

    public void waitForMenuActive(String menu) throws Exception {
        Browser.waitUntilVisibilityOfElement(SideMenuPageObjects.getMenuActive(menu), elementTimeout);
    }

    /**
     * Verify function
     * A Verify function is the one which return a boolean flag as a rust of the verification
     * Can be used in the Asserts
     * Can be used for conditional business flow based on data.
     */
    public boolean isMenuDisplayed(String menu) throws Exception {
        return Browser.isElementPresent(SideMenuPageObjects.getMenu(menu));
    }


    /**
     * Assert function
     * A Assert function is the one which does the Test Validation & Reports the same in Report
     * Always use addPassLabel
     */

    public void assertMenuExist(String menu) throws Exception {
        waitForMenu(menu);
        Assert.assertTrue(isMenuDisplayed(menu), menu + " - Menu not present.");
        logger.addPassLabel(menu + " - Menu displayed.");
    }
}
