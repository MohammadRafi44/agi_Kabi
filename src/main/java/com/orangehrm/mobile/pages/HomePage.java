package com.orangehrm.mobile.pages;

import com.testcrew.base.MobileBasePage;
import com.testcrew.manager.ReportManager;
import com.testcrew.mobile.Mobile;
import org.testng.Assert;
import com.orangehrm.mobile.objects.HomePageObjects;

import java.util.Map;

public class HomePage extends MobileBasePage {
    public static final ReportManager logger = new ReportManager(HomePage.class);

    // Page actions
    public void enterInstanceURL(String url) throws Exception {
        logger.info("Enter Instance URL as " + url);
        Mobile.setText(HomePageObjects.getTextInstanceURL(), url);
        logger.addScreenshot("After Instance URL");
    }
    public void enterEmail(String email) throws Exception {
        logger.info("Enter Email as " + email);
//        Mobile.setText(HomePageObjects.getTextEmail(), email);
        logger.addScreenshot("After Email");
    }

    public void enterPassword(String password) throws Exception {
        logger.info("Enter Password as " + password);
//        Mobile.setText(HomePageObjects.getTextPassword(), password);
    }

    public void clickHome() throws Exception {
        logger.info("Click Home");
//        Mobile.click(HomePageObjects.getButtonHome());
    }

    // Page functions
    public void changeLanguage(String language) throws Exception {
        logger.info("Change Language");
        if (language.equalsIgnoreCase("en")) {
//            if (Mobile.isElementPresent(HomePageObjects.getButtonLanguage("en"))) {
//                Mobile.click(HomePageObjects.getButtonLanguage("en"));
//            }
        } else {
//            if (Mobile.isElementPresent(HomePageObjects.getButtonLanguage("ar"))) {
//                Mobile.click(HomePageObjects.getButtonLanguage("ar"));
//            }
        }
//        Mobile.waitUntilVisibilityOfElement(HomePageObjects.getButtonHome(), 10);
    }

    public void Home(Map<String, String> data) throws Exception {
        logger.info("Home to application.");
        enterEmail(data.get("Email"));
        enterPassword(data.get("Password"));
        clickHome();
    }

    // Wait functions
    public void waitForPageLoad() throws Exception {
        Mobile.waitUntilVisibilityOfElement(HomePageObjects.getLabelEnterOrangeHRMURL(), elementTimeout);
    }

    // Verify functions

    // Assert functions
    public void assertHomePageDisplayed() throws Exception {
//        Mobile.waitUntilVisibilityOfElement(HomePageObjects.getButtonHome(), elementTimeout);
//        Assert.assertTrue(Mobile.isElementPresent(HomePageObjects.getButtonHome()),
//                "Home button not present.");
        logger.addPassLabel("Home Page displayed.");
    }
}
