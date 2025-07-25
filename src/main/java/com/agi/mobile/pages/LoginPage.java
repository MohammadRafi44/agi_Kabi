package com.agi.mobile.pages;

import com.agi.mobile.objects.HomePageObjects;
import com.agi.mobile.objects.LoginPageObjects;
import com.alghurair.base.MobileBasePage;
import com.alghurair.manager.ReportManager;
import com.alghurair.mobile.Mobile;
import com.alghurair.utility.TCRobot;
import io.appium.java_client.PerformsTouchActions;
import org.openqa.selenium.By;
import org.testng.Assert;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

import java.awt.event.KeyEvent;
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
    public void enterDriverid(String driverid) throws Exception {
        logger.info("Enter Driverid as " + driverid);
        Mobile.setText(LoginPageObjects.getTextDriverID(), driverid);
        logger.addScreenshot("After entering Driverid");
        Mobile.click(LoginPageObjects.getLabelHelloThere());
    }
    public void clickGenerateOtp() throws Exception {
        Mobile.waitForSeconds(2);
        logger.info("Click Generate OTP ");
        Mobile.waitUntilVisibilityOfElement(LoginPageObjects.getButtonGenerateOtp(), elementTimeout);
        Mobile.click(LoginPageObjects.getButtonGenerateOtp());
        logger.addScreenshot("After clicking on Generate OTP Button");
    }
//    public void enterOtp(String otp) throws Exception {
//        logger.info("Enter OTP as " + otp);
//        Mobile.setText(LoginPageObjects.getTextOtp(), otp);
//        logger.addScreenshot("After entering otp");
//    }
    public void enterPassword(String password) throws Exception {
        logger.info("Enter Password as " + password);
        Mobile.setText(LoginPageObjects.getTextPassword(), password);
    }

    public void clickLogin() throws Exception {
        logger.info("Click Login");
        Mobile.click(LoginPageObjects.getButtonLogin());
        Mobile.waitUntilVisibilityOfElement(HomePageObjects.getButtonMenu(),elementTimeout);
        logger.addScreenshot("After Login");
//        Mobile.waitForSeconds(10);
        assertWelcomeTextDisplayed();
    }
    public void clickUAT() throws Exception {
        logger.info("Click UAT");
        Mobile.click(LoginPageObjects.getBtnUAT());
        logger.addScreenshot("Clicked on UAT");
    }

    public void clickContinue() throws Exception {
        logger.info("Click Continue");
        Mobile.click(LoginPageObjects.getButtonContinue());
        logger.addScreenshot("After clicking on Continue button");

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
    public void enterOtp(String otp) throws Exception {
        TCRobot tc= new TCRobot();
        if (otp.length() != 6) {
            throw new IllegalArgumentException("OTP must be exactly 6 digits");
        }

        for (int i = 0; i < otp.length(); i++) {
            String digit = String.valueOf(otp.charAt(i));

//            // Tap at a safe location before interacting with each OTP field
//            TouchAction<?> action = new TouchAction<>((PerformsTouchActions) Mobile.driver);
//            action.tap(PointOption.point(920, 1100)).perform();

            // Scroll and interact with the OTP field
            By locator = LoginPageObjects.getOtpDigit(i);


            Mobile.click(locator);
            Mobile.getWebElement(locator).sendKeys(digit);
//            tc.keyPress(KeyEvent.VK_ESCAPE);
        }

        logger.info("Entered OTP: " + otp);
        logger.addScreenshot("After entering OTP");
//        Mobile.click(LoginPageObjects.getLabelHelloThere());
//        Thread.sleep(1000);
//        Mobile.click(LoginPageObjects.getButtonGenerateOtp());


    }




    /**
     * Wait function
     * A Wait function is the one which waits for certain event on that page.
     */
    public void waitForPageLoad() throws Exception {
        Mobile.waitUntilVisibilityOfElement(LoginPageObjects.getLabelLogin(), elementTimeout);
    }
    public void waitForUATButtonVisible() throws Exception {
        Mobile.waitUntilVisibilityOfElement(LoginPageObjects.getBtnUAT(), elementTimeout);
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
    public boolean isUATButtonDisplayed() throws Exception {
        return Mobile.isElementPresent(LoginPageObjects.getBtnUAT());
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

    public void assertUATButtonDisplayed() throws Exception {
        Mobile.waitUntilVisibilityOfElement(LoginPageObjects.getBtnUAT(), elementTimeout);
        Assert.assertTrue(Mobile.isElementDisplayed(LoginPageObjects.getBtnUAT()), "UAT button not present.");
        logger.addPassLabel("UAT button is displayed.");
    }
    public void assertErrorDisplayedInvalidCredentials() throws Exception {
        Mobile.waitUntilVisibilityOfElement(LoginPageObjects.getLabelErrorInvalidCredentials(), elementTimeout);
        logger.addScreenshot("Error Invalid credentials.");
        Assert.assertTrue(Mobile.isElementPresent(LoginPageObjects.getLabelErrorInvalidCredentials()), "Error message displayed.");
        logger.addPassLabel("Error displayed - Invalid credentials.");
    }
    public void assertWelcomeTextDisplayed() throws Exception {
        By welcomeTextLocator = LoginPageObjects.getTextWelcome();

        try {
            Mobile.waitUntilVisibilityOfElement(welcomeTextLocator, elementTimeout);

            if (Mobile.isElementPresent(welcomeTextLocator)) {
                logger.addScreenshot("✅ Welcome text is visible.");
                logger.addPassLabel("✅ Welcome text displayed.");
            } else {
                logger.addScreenshot("❌ Welcome text not visible.");
                logger.fail("❌ Expected 'Welcome' text not found on the screen.");
                throw new AssertionError("Expected 'Welcome' text not found on the screen.");
            }

        } catch (Exception e) {
            logger.fail("❌ Exception occurred while verifying 'Welcome' text: " + e.getMessage());
            logger.addScreenshot("❌ Exception during Welcome text validation");
            throw e;
        }
    }

    public void assertHomePageDisplayed() throws Exception {
        By homePageLocator = LoginPageObjects.getTextHomePage();

        try {
            Mobile.waitUntilVisibilityOfElement(homePageLocator, elementTimeout);

            if (Mobile.isElementPresent(homePageLocator)) {
                logger.addScreenshot("✅ Home Page is visible.");
                logger.addPassLabel("✅ Home Page displayed successfully.");
            } else {
                logger.addScreenshot("❌ Home Page not visible.");
                logger.fail("❌ Expected Home Page element not found.");
                throw new AssertionError("Expected Home Page element not found.");
            }

        } catch (Exception e) {
            logger.fail("❌ Exception occurred while verifying Home Page element: " + e.getMessage());
            logger.addScreenshot("❌ Exception during Home Page validation");
            throw e;
        }
    }



    public void assertUATButtonNotDisplayed() throws Exception {

        Assert.assertTrue(Mobile.isElementDisplayed(LoginPageObjects.getBtnUAT()), "UAT button not present.");
        logger.addPassLabel("UAT button is displayed.");
    }
}
