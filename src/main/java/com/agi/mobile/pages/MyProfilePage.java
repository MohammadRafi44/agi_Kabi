package com.agi.mobile.pages;

import com.agi.mobile.objects.HomePageObjects;
import com.agi.mobile.objects.LoginPageObjects;
import com.agi.mobile.objects.MyProfilePageObjects;
import com.alghurair.base.MobileBasePage;
import com.alghurair.manager.ReportManager;
import com.alghurair.mobile.Mobile;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

@Slf4j
public class MyProfilePage extends MobileBasePage {
    public static final ReportManager logger = new ReportManager(MyProfilePage.class);

    /**
     * Page action
     * A Page action is the granular level of action performed on that page.
     */
    public void enterInstanceURL(String url) throws Exception {
        logger.info("Enter Instance URL as " + url);
        Mobile.setText(HomePageObjects.getTextInstanceURL(), url);
        logger.addScreenshot("After Instance URL");
    }


    public void clickLogout() throws Exception {
        logger.info("Click Menu");
        Mobile.click(MyProfilePageObjects.getButtonLogout());
        clickYes();
        logger.addScreenshot("After clicking on Logout");
    }

    public void clickYes() throws Exception {
        logger.info("Click Yes");
        Mobile.waitUntilElementToBeClickable(MyProfilePageObjects.getButtonYes(), elementTimeout);
        Mobile.click(MyProfilePageObjects.getButtonYes());
        Mobile.waitUntilVisibilityOfElement(LoginPageObjects.getBtnUAT(),elementTimeout);
        logger.addScreenshot("After clicking on Yes");
    }

    /**
     * Page function
     * A Page function is the group of action.
     */
    public void continueToLogin(String url) throws Exception {
        logger.info("Continue to Login");
//        enterInstanceURL(url);
//        clickContinue();
    }

    /**
     * Wait function
     * A Wait function is the one which waits for certain event on that page.
     */
    public void waitForPageLoad() throws Exception {
        Mobile.waitUntilVisibilityOfElement(HomePageObjects.getLabelEnterAgiURL(), elementTimeout);
    }

    /**
     * Verify function
     * A Verify function is the one which return a boolean flag as a rust of the verification
     * Can be used in the Asserts
     * Can be used for conditional business flow based on data.
     */
    public boolean isHomePageDisplayed() throws Exception {
        return Mobile.isElementDisplayed(HomePageObjects.getLabelEnterAgiURL());
    }

    /**
     * Assert function
     * A Assert function is the one which does the Test Validation & Reports the same in Report
     * Always use addPassLabel
     */
    public void assertHomePageDisplayed() throws Exception {
        Mobile.waitUntilVisibilityOfElement(HomePageObjects.getLabelEnterAgiURL(), elementTimeout);
        Assert.assertTrue(isHomePageDisplayed(), "Home page not displayed.");
        logger.addPassLabel("Home Page displayed.");
    }
}
