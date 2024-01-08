package com.orangehrm.web.pages;

import com.orangehrm.web.objects.MyInfoPageObjects;
import com.testcrew.base.WebBasePage;
import com.testcrew.manager.ReportManager;
import com.testcrew.web.Browser;
import org.testng.Assert;

import java.util.Map;

public class MyInfoPage extends WebBasePage {
    public static final ReportManager logger = new ReportManager(MyInfoPage.class);

    /**
     * Page action
     * A Page action is the granular level of action performed on that page.
     */
    public void enterFirstName(String firstName) throws Exception {
        logger.info("Enter First Name as " + firstName);
        Browser.clearText(MyInfoPageObjects.getTextFirstName());
        Browser.setText(MyInfoPageObjects.getTextFirstName(), firstName);
    }

    public void enterMiddleName(String middleName) throws Exception {
        logger.info("Enter Middle Name as " + middleName);
        Browser.clearText(MyInfoPageObjects.getTextMiddleName());
        Browser.setText(MyInfoPageObjects.getTextMiddleName(), middleName);
    }

    public void enterLastName(String lastName) throws Exception {
        logger.info("Enter Last Name as " + lastName);
        Browser.clearText(MyInfoPageObjects.getTextLastName());
        Browser.setText(MyInfoPageObjects.getTextLastName(), lastName);
    }

    public void clickSave() throws Exception {
        logger.info("Click Save");
        Browser.executeJSScroll(500);
        Browser.click(MyInfoPageObjects.getButtonSave());
    }

    /**
     * Page function
     * A Page function is the group of action.
     */
    public void openMenuPage(Map<String, String> data) throws Exception {

    }

    /**
     * Wait function
     * A Wait function is the one which waits for certain event on that page.
     */
    public void waitForPageLoad() throws Exception {
        Browser.waitUntilVisibilityOfElement(MyInfoPageObjects.getElementPersonalDetails(), elementTimeout);
    }

    /**
     * Verify function
     * A Verify function is the one which return a boolean flag as a rust of the verification
     * Can be used in the Asserts
     * Can be used for conditional business flow based on data.
     */
    public boolean isMyInfoPageDisplayed() throws Exception {
        return Browser.isElementDisplayed(MyInfoPageObjects.getElementPersonalDetails());
    }

    public boolean isSuccessMessageDisplayed() throws Exception {
        return Browser.isElementDisplayed(MyInfoPageObjects.getElementSuccess());
    }


    /**
     * Assert function
     * A Assert function is the one which does the Test Validation & Reports the same in Report
     * Always use addPassLabel
     */

    public void assertMyInfoPageDisplayed() throws Exception {
        Assert.assertTrue(isMyInfoPageDisplayed(), "My Info page not displayed.");
        logger.addPassLabel("My Info page displayed");
    }

    public void assertSuccessMessageDisplayed() throws Exception {
        Browser.waitForSeconds(1);
        logger.addScreenshot("Success Banner");
        Assert.assertTrue(isSuccessMessageDisplayed(), "Success not displayed.");
        logger.addPassLabel("Success displayed");
    }
}
