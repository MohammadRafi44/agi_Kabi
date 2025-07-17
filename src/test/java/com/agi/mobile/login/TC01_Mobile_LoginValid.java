package com.agi.mobile.login;

import com.agi.mobile.base.AgiMobileTest;
import com.alghurair.manager.TestConfigManager;
import org.testng.annotations.Test;

import java.util.Map;

public class TC01_Mobile_LoginValid extends AgiMobileTest {

    @Test(dataProvider = "testDataProvider")
    public void mobileLoginValid(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data.toString());
        app.homePage.waitForPageLoad();

        logger.info("Step 01: Assert Home page displayed");
        app.homePage.assertHomePageDisplayed();

        logger.info("Step 02: Continue to Login");
        app.homePage.continueToLogin(TestConfigManager.getTestSettingsWebAppURL());

        logger.info("Step 03: Assert Login page displayed");
        app.loginPage.waitForPageLoad();
        app.loginPage.assertLoginPageDisplayed();

        logger.info("Step 04: Login to Application Enter Username, Enter Password, click Login");
        app.loginPage.enterUsername(data.get("Username"));
        app.loginPage.enterPassword(data.get("Password"));
        app.loginPage.clickLogin();

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
}
