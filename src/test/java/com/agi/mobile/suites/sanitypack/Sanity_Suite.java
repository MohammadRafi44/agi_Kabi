package com.agi.mobile.suites.sanitypack;

import com.agi.mobile.base.AgiMobileTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Map;

@Slf4j
public class Sanity_Suite extends AgiMobileTest {

    // TC_MOB_1
    @Test(dataProvider = "testDataProvider",
            description = "Scenario: Login with valid Driver ID on Mobile\n" +
                    "- Given a mobile user exists with a valid Driver ID\n" +
                    "- When the user logs in with correct credentials\n" +
                    "- Then the login should be successful\n" +
                    "- And the user dashboard should be displayed")
    public void loginValidDriverID(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data :" + data.toString());
        logger.info("Step 01 : Select UAT environment and click UAT button ");
            app.loginPage.selectEnvironment();
        logger.info("Step 02 : Enter Driver Id ");
            app.loginPage.enterDriverid(data.get("Driverid"));
        logger.info("Step 03 : Click on Generate OTP Button ");
            app.loginPage.clickGenerateOtp();
        logger.info("Step 04 : Enter OTP ");
            app.loginPage.enterOtp(data.get("OTP"));
        logger.info("Step 05 : Click Login ");
            app.loginPage.login();
//        logger.info("Step 06 : Click Continue");
//        app.loginPage.clickContinue();
//        logger.info("Step 07: Home page is displayed");
//        app.loginPage.assertHomePageDisplayed();
        logger.info("Step 08: Click on Menu");
            app.homePage.clickMenu();
        logger.info("Step 09: Click on My Profile");
            app.menuOptions.clickMyProfile();
        logger.info("Step 09: Click on Logout");
            app.myProfilePage.clickLogout();
    }

    // TC_MOB_2
    @Test(dataProvider = "testDataProvider",
    description = "Scenario: Login with invalid Driver ID on Mobile\n" +
            "- Given a mobile user enters an invalid Driver ID\n" +
            "- When the user attempts to log in\n" +
            "- Then an error message should be displayed indicating the user does not exist")
    public void loginInvalidDriverID(Map<String, String> data) throws Exception {
    logger.info("Step 00: Test Data :" + data.toString());
    logger.info("Step 01 : Select UAT environment and click UAT button ");
        app.loginPage.selectEnvironment();
    logger.info("Step 02 : Enter Invalid Driver Id ");
        app.loginPage.enterDriverid(data.get("Driverid"));
    logger.info("Step 03 : Click on Generate OTP and validate Invalid Driver ID Login message");
        app.loginPage.validateInvalidDriverLogin();
    }

    // TC_MOB_3
    @Test(dataProvider = "testDataProvider",
    description = "Scenario: Login with invalid OTP on Mobile\n" +
            "- Given a mobile user has requested an OTP\n" +
            "- When the user enters an invalid or expired OTP\n" +
            "- Then an error message should be shown indicating the OTP is invalid or expired")
    public void loginInvalidOtp(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data :" + data.toString());
        logger.info("Step 01 : Select UAT environment and click UAT button ");
            app.loginPage.selectEnvironment();
        logger.info("Step 02 : Enter Driver Id ");
            app.loginPage.enterDriverid(data.get("Driverid"));
            app.loginPage.clickGenerateOtp();
        logger.info("Step 03 : Click on Generate OTP Button ");
        logger.info("Step 04 : Enter Invalid OTP ");
            app.loginPage.enterOtp(data.get("InvalidOTP"));
        logger.info("Step 05 : Login & Verify Invalid OTP login message is shown");
            app.loginPage.validateInvalidOtpLogin();

    }
}






