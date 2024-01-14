package com.orangehrm.web.login;

import com.orangehrm.web.base.OrangeHRMWebTest;
import com.testcrew.manager.TestDataManager;
import org.testng.annotations.Test;

import java.util.Map;

public class TC02_Web_LoginInvalid extends OrangeHRMWebTest {

    @Test(dataProvider = "testDataProvider")
    public void loginInvalid(Map<String, String> data) throws Exception {
        data.putAll(TestDataManager.readDependantTestData("test2"));
        data.putAll(TestDataManager.readDependantGlobalTestData("Global"));
        logger.info("Step 00: Test Data : " + data.toString());
        app.openApplication(data);
        logger.info("Step 01: Login to Application Using Page functions");
        app.loginPage.login(data);
        logger.info("Step 02: Verify Error displayed - Invalid credentials");
        app.loginPage.assertErrorDisplayedInvalidCredentials();
        logger.info(data.toString());
    }
}
