package com.orangehrm.api.login;

import com.orangehrm.api.base.OrangeHRMAPITest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class TC02_API_LoginInvalid extends OrangeHRMAPITest {

    @Test(dataProvider = "testDataProvider")
    public void apiLoginInvalid(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data.toString());

        logger.info("Step 01: Get Login API Details");
        data.putAll(api.loginApi.getLoginAPIDetails("LoginInvalid"));
        HttpResponse<JsonNode> response = api.loginApi.login(data);

        logger.info("Step 02: Validate Complete Response");
        api.loginApi.assertLoginResponse(data);

        logger.info("Step 03: Validate Specific Response");
        String reason = String.valueOf(response.getBody().getObject().getString("reason"));
        Assert.assertEquals(reason, "Bad credentials", "Response body reason.");
        logger.pass("Response body reason is " + reason);
    }
}
