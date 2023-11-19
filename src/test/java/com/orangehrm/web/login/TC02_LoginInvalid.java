package com.orangehrm.web.login;

import com.orangehrm.web.base.OrangeHRMWebTest;
import org.testng.annotations.Test;

import java.util.Map;

public class TC02_LoginInvalid extends OrangeHRMWebTest {

    @Test(dataProvider = "testDataProvider")
    public void loginInvalid(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data.toString());
        app.openApplication(data);
        logger.info("Step 01: Login to Application Using Page functions");
        app.loginPage.login(data);
        logger.info("Step 02: Verify Error displayed - Invalid credentials");
        app.loginPage.assertErrorDisplayedInvalidCredentials();
    }
}
