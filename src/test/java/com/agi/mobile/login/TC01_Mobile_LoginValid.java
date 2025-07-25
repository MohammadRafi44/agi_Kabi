package com.agi.mobile.login;

import com.agi.mobile.base.AgiMobileTest;
import com.alghurair.manager.TestConfigManager;
import com.alghurair.mobile.Mobile;
import com.alghurair.utility.TCRobot;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.testng.annotations.Test;

import java.awt.event.KeyEvent;
import java.util.Map;

import static com.alghurair.mobile.Mobile.driver;

public class TC01_Mobile_LoginValid extends AgiMobileTest {

    @Test(dataProvider = "testDataProvider")
    public void loginValidDriverID(Map<String, String> data) throws Exception {
//        logger.info("Step 00: Test Data : " + data.toString());
////        app.homePage.waitForPageLoad();
//
//        logger.info("Step 01: Assert Home page displayed");
//        app.homePage.assertHomePageDisplayed();
//
////        logger.info("Step 02: Continue to Login");
////        app.homePage.continueToLogin(TestConfigManager.getTestSettingsWebAppURL());
//
        logger.info("Step 00: Test Data :" + data.toString());
        logger.info("Step 01 : Select UAT environment and click UAT button ");
        app.loginPage.waitForUATButtonVisible();
        app.loginPage.assertUATButtonDisplayed();
        app.loginPage.clickUAT();

        logger.info("Step 02 : Enter Driver Id ");
        app.loginPage.enterDriverid(data.get("Driverid"));

        logger.info("Step 03 : Click on Generate OTP Button ");
        app.loginPage.clickGenerateOtp();

        logger.info("Step 04 : Enter OTP ");
        app.loginPage.enterOtp(data.get("OTP"));

        logger.info("Step 05 : Click Login ");
        app.loginPage.clickLogin();

        logger.info("Step 06 : Click Continue");
        app.loginPage.clickContinue();

        logger.info("Step 07: Home page is displayed");
        app.loginPage.assertHomePageDisplayed();

        logger.info("Step 08: Click on Menu");
        app.homePage.clickMenu();

        logger.info("Step 09: Click on My Profile");
        app.menuOptions.clickMyProfile();

        logger.info("Step 09: Click on Logout");
        app.myProfilePage.clickLogout();
















//        app.loginPage.waitForPageLoad();


//        app.loginPage.waitForUATButtonVisible();
////                app.loginPage.assertLoginPageDisplayed();
//        app.loginPage.assertUATButtonDisplayed();
//        app.loginPage.clickUAT();
////
////        logger.info("Step 04: Login to Application Enter Username, Enter Password, click Login");
////        app.loginPage.enterUsername(data.get("Username"));
//        app.loginPage.enterDriverid(data.get("Driverid"));
//        app.loginPage.clickGenerateOtp();

//        app.loginPage.enterPassword(data.get("Password"));
//        app.loginPage.clickLogin();

//        logger.info("Step 05: Assert Side Menu 'My Info' displayed.");
//        app.sideMenu.assertMenuExist("My Info");
//
//        logger.info("Step 06: Open My Info Page.");
//        app.sideMenu.openMenuPage("My Info");
//        app.myInfoPage.waitForPageLoad();
//
//        logger.info("Step 07: Assert My Info Page displayed.");
//        app.myInfoPage.assertMyInfoPageDisplayed();
//
//        logger.info("Step 08: Enter/Select the following General Firstname, MiddleName, LastName");
//        app.myInfoPage.enterFirstName(data.get("FirstName"));
//        app.myInfoPage.enterMiddleName(data.get("MiddleName"));
//        app.myInfoPage.enterLastName(data.get("LastName"));
//
//        logger.info("Step 09: Click the Save button");
//        app.myInfoPage.clickSave();
//
//        logger.info("Step 10: Assert Success Message displayed");
//        app.myInfoPage.assertSuccessMessageDisplayed();
    }
        @Test(dataProvider = "testDataProvider")
        public void loginInvalidDriverID(Map<String, String> data) throws Exception {

        logger.info("Step 00: Test Data :" + data.toString());
        logger.info("Step 01 : Select UAT environment and click UAT button ");
        app.loginPage.waitForUATButtonVisible();
        app.loginPage.assertUATButtonDisplayed();
        app.loginPage.clickUAT();

        logger.info("Step 02 : Enter Driver Id ");
        app.loginPage.enterDriverid(data.get("Driverid"));

        logger.info("Step 03 : Click on Generate OTP Button ");
        app.loginPage.clickGenerateOtp();

        logger.info("Step 04 : Enter OTP ");
        app.loginPage.enterOtp(data.get("OTP"));

        logger.info("Step 05 : Click Login ");
        app.loginPage.clickLogin();

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
}



