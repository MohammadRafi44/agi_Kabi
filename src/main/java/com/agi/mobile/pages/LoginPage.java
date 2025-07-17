package com.agi.mobile.pages;

import com.alghurair.base.MobileBasePage;
import com.alghurair.manager.ReportManager;
import com.alghurair.mobile.Mobile;
import com.agi.mobile.objects.LoginPageObjects;
import org.testng.Assert;

import java.util.Map;

public class LoginPage extends MobileBasePage {
    public static final ReportManager logger = new ReportManager(LoginPage.class);

    /**
     * Page action
     * A Page action is the granular level of action performed on that page.
     * Example:
     * 1. click login button
     * 2. enter username
     */
    public void enterUsername(String username) throws Exception {
        logger.info("Enter Username as " + username);
        Mobile.setText(LoginPageObjects.getTextUsername(), username);
        logger.addScreenshot("After Username");
    }

    public void enterPassword(String password) throws Exception {
        logger.info("Enter Password as " + password);
        Mobile.setText(LoginPageObjects.getTextPassword(), password);
    }

    public void clickLogin() throws Exception {
        logger.info("Click Login");
        Mobile.click(LoginPageObjects.getButtonLogin());
    }

    /**
     * Page function
     * A Page function is the group of action.
     */
    public void login(Map<String, String> data) throws Exception {
        logger.info("Login to application.");
        enterUsername(data.get("Username"));
        enterPassword(data.get("Password"));
        clickLogin();
    }

    /**
     * Wait function
     * A Wait function is the one which waits for certain event on that page.
     */
    public void waitForPageLoad() throws Exception {
        Mobile.waitUntilVisibilityOfElement(LoginPageObjects.getLabelLogin(), elementTimeout);
    }

    /**
     * Verify function
     * A Verify function is the one which return a boolean flag as a rust of the verification
     * Can be used in the Asserts
     * Can be used for conditional business flow based on data.
     */
    public boolean isLoginPageDisplayed() throws Exception {
        return Mobile.isElementPresent(LoginPageObjects.getButtonLogin());
    }


    /**
     * Assert function
     * A Assert function is the one which does the Test Validation & Reports the same in Report
     * Always use addPassLabel
     */

    public void assertLoginPageDisplayed() throws Exception {
        Mobile.waitUntilVisibilityOfElement(LoginPageObjects.getButtonLogin(), elementTimeout);
        Assert.assertTrue(isLoginPageDisplayed(), "Login button not present.");
        logger.addPassLabel("Login Page displayed.");
    }

    public void assertErrorDisplayedInvalidCredentials() throws Exception {
        Mobile.waitUntilVisibilityOfElement(LoginPageObjects.getLabelErrorInvalidCredentials(), elementTimeout);
        logger.addScreenshot("Error Invalid credentials.");
        Assert.assertTrue(Mobile.isElementPresent(LoginPageObjects.getLabelErrorInvalidCredentials()), "Error message displayed.");
        logger.addPassLabel("Error displayed - Invalid credentials.");
    }
}
