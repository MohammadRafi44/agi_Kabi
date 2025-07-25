package com.agi.mobile.login;

import com.agi.mobile.base.AgiMobileTest;
import com.alghurair.manager.TestConfigManager;
import org.testng.annotations.Test;

import java.util.Map;

public class TC01_Mobile_LoginInvalid extends AgiMobileTest {

    @Test(dataProvider = "testDataProvider")
    public void mobileLoginInvalid(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data.toString());

        app.homePage.waitForPageLoad();

        logger.info("Step 01: Assert Home page displayed");
        app.homePage.assertHomePageDisplayed();

        logger.info("Step 02: Enter Instance URL & Continue");
        app.homePage.enterInstanceURL(TestConfigManager.getTestSettingsWebAppURL());
        app.homePage.clickContinue();

        logger.info("Step 03: Login to Application Using Page functions");
        app.loginPage.waitForPageLoad();
        app.loginPage.login(data);

        logger.info("Step 04: Verify Error displayed - Invalid credentials");
        app.loginPage.assertErrorDisplayedInvalidCredentials();
    }
}
