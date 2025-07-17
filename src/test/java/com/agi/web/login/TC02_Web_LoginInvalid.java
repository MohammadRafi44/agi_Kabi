package com.agi.web.login;

import com.agi.web.base.AgiWebTest;
import com.alghurair.manager.TestDataManager;
import org.testng.annotations.Test;

import java.util.Map;

public class TC02_Web_LoginInvalid extends AgiWebTest {

    @Test(dataProvider = "testDataProvider")
    public void loginInvalid(Map<String, String> data) throws Exception {
        data.putAll(TestDataManager.readDependantTestData("test2"));
        data.putAll(TestDataManager.readDependantGlobalTestData("Global"));
        logger.info("Step 00: Test Data : " + data.toString());
        app.openApplication(data);
        logger.info("Step 01: Login to Application Using Page functions");
        app.loginPage.login(data);
        logger.info("Step 02: Verify Error displayed - Invalid credentials");
        logger.fail("test Failed");
        app.loginPage.assertErrorDisplayedInvalidCredentials();
        logger.info(data.toString());
    }
}
