package com.orangehrm.api.login;

import java.util.Map;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.orangehrm.api.base.OrangeHRMAPITest;

public class TC01_LoginValid extends OrangeHRMAPITest {

    @Test(dataProvider = "testDataProvider")
    public void loginValid(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data.toString());
        logger.info("Step 01: Login to Application Enter Email, Enter Password, click Login");
        HttpResponse<JsonNode> response = api.loginApi.login(data);
        api.loginApi.assertLoginResponse(response);
        logger.info("Step 02: Validate Response");
        String token = String.valueOf(response.getBody().getObject().getString("token"));
        Assert.assertNotEquals(token, "", "Response body token.");
        logger.pass("Response body token is " + token);
    }
}
