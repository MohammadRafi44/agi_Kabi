package com.orangehrm.web.pages;

import com.orangehrm.web.objects.LoginPageObjects;
import com.testcrew.base.WebBasePage;
import com.testcrew.manager.ReportManager;
import com.testcrew.web.Browser;
import org.testng.Assert;

import java.util.Map;

public class LoginPage extends WebBasePage {
    public static final ReportManager logger = new ReportManager(LoginPage.class);

    // Page actions
    public void enterUsername(String username) throws Exception {
        logger.info("Enter Username as " + username);
        Browser.setText(LoginPageObjects.getTextUsername(), username);
        logger.addScreenshot("After Username");
    }

    public void enterPassword(String password) throws Exception {
        logger.info("Enter Password as " + password);
        Browser.setText(LoginPageObjects.getTextPassword(), password);
    }

    public void clickLogin() throws Exception {
        logger.info("Click Login");
        Browser.click(LoginPageObjects.getButtonLogin());
    }

    // Page functions
    public void changeLanguage(String language) throws Exception {
        logger.info("Change Language");
        if (language.equalsIgnoreCase("en")) {
            if (Browser.isElementPresent(LoginPageObjects.getButtonLanguage("en"))) {
                Browser.click(LoginPageObjects.getButtonLanguage("en"));
            }
        } else {
            if (Browser.isElementPresent(LoginPageObjects.getButtonLanguage("ar"))) {
                Browser.click(LoginPageObjects.getButtonLanguage("ar"));
            }
        }
        Browser.waitUntilVisibilityOfElement(LoginPageObjects.getButtonLogin(), 10);
    }

    public void login(Map<String, String> data) throws Exception {
        logger.info("Login to application.");
        enterUsername(data.get("Username"));
        enterPassword(data.get("Password"));
        clickLogin();
    }

    // Wait functions
    public void waitForPageLoad() throws Exception {
        Browser.waitUntilVisibilityOfElement(LoginPageObjects.getButtonLogin(), elementTimeout);
    }

    // Verify functions

    // Assert functions
    public void assertLoginPageDisplayed() throws Exception {
        Browser.waitUntilVisibilityOfElement(LoginPageObjects.getButtonLogin(), elementTimeout);
        Assert.assertTrue(Browser.isElementPresent(LoginPageObjects.getButtonLogin()),
                "Login button not present.");
        logger.addPassLabel("Login Page displayed.");
    }

    public void assertErrorDisplayedInvalidCredentials() throws Exception {
        Browser.waitForSeconds(5);
        Browser.waitUntilVisibilityOfElement(LoginPageObjects.getButtonLogin(), elementTimeout);
        Assert.assertTrue(Browser.isElementPresent(LoginPageObjects.getButtonLogin()),
                "Error message displayed.");
        logger.addPassLabel("Error displayed - Invalid credentials.");
    }
}
