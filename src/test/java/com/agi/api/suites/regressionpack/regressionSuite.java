package com.agi.api.suites.regressionpack;

import com.agi.api.base.AgiAPITest;
import com.alghurair.api.RestAssuredAPI;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.alghurair.api.RestAssuredAPI.ApiResponse;


import java.util.Map;

public class regressionSuite extends AgiAPITest {

    @Test(dataProvider = "testDataProvider")
    public void cafmCreateWorkOrder(Map<String,String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data);

        logger.info("Step 01: Get CAFM API details from RunManager");
        data.putAll(api.cafmApi.getCafmWorkOrderAPIDetails("CreateWorkOrderCAFM"));

        logger.info("Step 02: Call CreateWorkOrder");
        //Another way of calling
        //ApiResponse response = api.cafmApi.cafmCreateWorkOrder(data);
        //with JDK21
        var response = api.cafmApi.cafmCreateWorkOrder(data);

        logger.info("Step 03: Validate response (status + fields)");
        api.cafmApi.assertCafmCreateWorkOrderResponse(data, response);


       /* logger.info("Step 04: Validate a specific field");
        String msg = response.jsonString("Data.WorkOrders[0].ResponseMessage");
        org.testng.Assert.assertTrue(msg != null && !msg.isBlank(), "ResponseMessage present");
        logger.pass("ResponseMessage: " + msg);*/
    }

   /* @Test(dataProvider = "testDataProvider", description =
            "Scenario: Create a new Work Order with valid details\n" +
                    "- Given a user provides valid work order details\n" +
                    "- When the user sends a request to the Create Work Order API\n" +
                    "- Then the system should respond with status code 200\n" +
                    "- And the response should confirm that the work order is created successfully")
    public void cafmCreateWorkOrder(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data.toString());

        logger.info("Step 01: Get Login API Details");
        data.putAll(api.cafmApi.getCafmWorkOrderAPIDetails("CreateWorkOrderCAFM"));
        HttpResponse<JsonNode> response = api.cafmApi.cafmCreateWorkOrder(data);
//        logger.info(String.valueOf(response));

        logger.info("Step 02: Validate Complete Response");
        api.cafmApi.assertCafmCreateWorkOrderResponse(data);

        logger.info("Step 03: Validate Specific Response");

        String actualResMsg = new JSONObject(response).getJSONObject("Data").getJSONArray("WorkOrders").getJSONObject(0).getString("ResponseMessage");
        Assert.assertNotEquals(actualResMsg, "", "Word order creation Response Message.");

        // Cascade API Possible

    }*/

/*    @Test(dataProvider = "testDataProvider", description =
            "Scenario: Update an existing Work Order with valid details\n" +
                    "- Given a user provides valid updates for an existing work order\n" +
                    "- When the user sends a request to the Update Work Order API\n" +
                    "- Then the system should respond with status code 200\n" +
                    "- And the response should confirm that the work order is updated successfully")
    public void cafmUpdateWorkOrder(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data.toString());

        logger.info("Step 01: Get Login API Details");
        data.putAll(api.loginApi.getLoginAPIDetails("CreateWorkOrderCAFM"));
        HttpResponse<JsonNode> response = api.loginApi.login(data);

        logger.info("Step 02: Validate Complete Response");
        api.loginApi.assertLoginResponse(data);

//        logger.info("Step 03: Validate Specific Response");
//        String reason = String.valueOf(response.getBody().getObject().getString("reason"));
//        Assert.assertEquals(reason, "Bad credentials", "Response body reason.");
//        logger.pass("Response body reason is " + reason);
    }*/
}
