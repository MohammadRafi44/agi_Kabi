package com.agi.api.suites.regressionpack;

import com.agi.api.base.AgiAPITest;
import org.testng.annotations.Test;

import java.util.Map;

public class RelyApp extends AgiAPITest {

    @Test(dataProvider = "testDataProvider")
    public void retrieveEmployeeDetails(Map<String,String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data);

        logger.info("Step 01: Get retrieve Employee Details API details from RunManager");
        data.putAll(api.retrieveEmployeeDetailsApi.getAPIDetails("RetrieveEmployeeDetails"));

        logger.info("Step 02: Call Retrieve Employee Details API");
        //Another way of calling
        //ApiResponse response = api.cafmApi.cafmCreateWorkOrder(data);
        //with JDK21
        var response = api.retrieveEmployeeDetailsApi.retrieveEmployeeDetails(data);

        logger.info("Step 03: Validate response (status + fields)");
        api.retrieveEmployeeDetailsApi.assertRetrieveEmployeeDetails(data, response);


       /* logger.info("Step 04: Validate a specific field");
        String msg = response.jsonString("Data.WorkOrders[0].ResponseMessage");
        org.testng.Assert.assertTrue(msg != null && !msg.isBlank(), "ResponseMessage present");
        logger.pass("ResponseMessage: " + msg);*/
    }

    @Test(dataProvider = "testDataProvider")
    public void retrieveEmergencyContactsDetail(Map<String,String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data);

        logger.info("Step 01: Get RetrieveEmergencyContactsDetail details from RunManager");
        data.putAll(api.retrieveEmergencyContactsDetailApi.getAPIDetails("RetrieveEmergencyContactsDetail"));

        logger.info("Step 02: Call RetrieveEmergencyContactsDetail Details API");
        //Another way of calling
        //ApiResponse response = api.cafmApi.cafmCreateWorkOrder(data);
        //with JDK21
        var response = api.retrieveEmergencyContactsDetailApi.retrieveEmergencyContactsDetail(data);

        logger.info("Step 03: Validate response (status + fields)");
        api.retrieveEmergencyContactsDetailApi.assertRetrieveEmergencyContactsDetail(data, response);


       /* logger.info("Step 04: Validate a specific field");
        String msg = response.jsonString("Data.WorkOrders[0].ResponseMessage");
        org.testng.Assert.assertTrue(msg != null && !msg.isBlank(), "ResponseMessage present");
        logger.pass("ResponseMessage: " + msg);*/
    }

    @Test(dataProvider = "testDataProvider")
    public void retrieveEmployeeRecordPassPort(Map<String,String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data);

        logger.info("Step 01: Get Retrieve Employee Record details for Passport from RunManager");
        data.putAll(api.retrieveEmployeeRecord_api.getAPIDetails("RetrieveEmployeeRecordPassport"));

        logger.info("Step 02: Call Retrieve Employee Record Details for Passport - API");
        //Another way of calling
        //ApiResponse response = api.cafmApi.cafmCreateWorkOrder(data);
        //with JDK21
        var response = api.retrieveEmployeeRecord_api.retrieveEmployeeRecord(data);

        logger.info("Step 03: Validate response (status + fields)");
        api.retrieveEmployeeRecord_api.assertRetrieveEmployeeRecord(data, response);


       /* logger.info("Step 04: Validate a specific field");
        String msg = response.jsonString("Data.WorkOrders[0].ResponseMessage");
        org.testng.Assert.assertTrue(msg != null && !msg.isBlank(), "ResponseMessage present");
        logger.pass("ResponseMessage: " + msg);*/
    }

    @Test(dataProvider = "testDataProvider")
    public void retrieveEmployeeRecordEmiratesID(Map<String,String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data);

        logger.info("Step 01: Get Retrieve Employee Record details for Passport from RunManager");
        data.putAll(api.retrieveEmployeeRecord_api.getAPIDetails("RetrieveEmployeeRecordEmiratesId"));

        logger.info("Step 02: Call Retrieve Employee Record Details for Passport - API");
        //Another way of calling
        //ApiResponse response = api.cafmApi.cafmCreateWorkOrder(data);
        //with JDK21
        var response = api.retrieveEmployeeRecord_api.retrieveEmployeeRecord(data);

        logger.info("Step 03: Validate response (status + fields)");
        api.retrieveEmployeeRecord_api.assertRetrieveEmployeeRecord(data, response);


       /* logger.info("Step 04: Validate a specific field");
        String msg = response.jsonString("Data.WorkOrders[0].ResponseMessage");
        org.testng.Assert.assertTrue(msg != null && !msg.isBlank(), "ResponseMessage present");
        logger.pass("ResponseMessage: " + msg);*/
    }

    @Test(dataProvider = "testDataProvider")
    public void retrieveEmployeeRecordResidenceVisa(Map<String,String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data);

        logger.info("Step 01: Get retrieve Employee Record ResidenceVisa from RunManager");
        data.putAll(api.retrieveEmployeeRecord_api.getAPIDetails("RetrieveEmployeeRecordResidenceVisa"));

        logger.info("Step 02: Call Retrieve Employee Record Details for Passport - API");
        //Another way of calling
        //ApiResponse response = api.cafmApi.cafmCreateWorkOrder(data);
        //with JDK21
        var response = api.retrieveEmployeeRecord_api.retrieveEmployeeRecord(data);

        logger.info("Step 03: Validate response (status + fields)");
        api.retrieveEmployeeRecord_api.assertRetrieveEmployeeRecord(data, response);


       /* logger.info("Step 04: Validate a specific field");
        String msg = response.jsonString("Data.WorkOrders[0].ResponseMessage");
        org.testng.Assert.assertTrue(msg != null && !msg.isBlank(), "ResponseMessage present");
        logger.pass("ResponseMessage: " + msg);*/
    }

    @Test(dataProvider = "testDataProvider")
    public void retrieveEmployeeRecordSalaryCertificate(Map<String,String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data);

        logger.info("Step 01: Get retrieve Salary Certificate Record from RunManager");
        data.putAll(api.retrieveEmployeeRecord_api.getAPIDetails("RetrieveEmployeeRecordSalaryCertificate"));

        logger.info("Step 02: Call Retrieve Employee record for Salary Certificate Record - API");
        //Another way of calling
        //ApiResponse response = api.cafmApi.cafmCreateWorkOrder(data);
        //with JDK21
        var response = api.retrieveEmployeeRecord_api.retrieveEmployeeRecord(data);

        logger.info("Step 03: Validate response (status + fields)");
        api.retrieveEmployeeRecord_api.assertRetrieveEmployeeRecord(data, response);


       /* logger.info("Step 04: Validate a specific field");
        String msg = response.jsonString("Data.WorkOrders[0].ResponseMessage");
        org.testng.Assert.assertTrue(msg != null && !msg.isBlank(), "ResponseMessage present");
        logger.pass("ResponseMessage: " + msg);*/
    }

    @Test(dataProvider = "testDataProvider")
    public void retrieveEmployeeRecordDriversLicense(Map<String,String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data);

        logger.info("Step 01: Get retrieve employee record api for Drivers License from RunManager");
        data.putAll(api.retrieveEmployeeRecord_api.getAPIDetails("RetrieveEmployeeRecordDriversLicense"));

        logger.info("Step 02: Call Retrieve Employee record for Drivers License - API");
        //Another way of calling
        //ApiResponse response = api.cafmApi.cafmCreateWorkOrder(data);
        //with JDK21
        var response = api.retrieveEmployeeRecord_api.retrieveEmployeeRecord(data);

        logger.info("Step 03: Validate response (status + fields)");
        api.retrieveEmployeeRecord_api.assertRetrieveEmployeeRecord(data, response);


       /* logger.info("Step 04: Validate a specific field");
        String msg = response.jsonString("Data.WorkOrders[0].ResponseMessage");
        org.testng.Assert.assertTrue(msg != null && !msg.isBlank(), "ResponseMessage present");
        logger.pass("ResponseMessage: " + msg);*/
    }

    @Test(dataProvider = "testDataProvider")
    public void retrieveEmployeeRecordHealthInsurance(Map<String,String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data);

        logger.info("Step 01: Get retrieve employee record api for Health Insurance from RunManager");
        data.putAll(api.retrieveEmployeeRecord_api.getAPIDetails("RetrieveEmployeeRecordHealthInsurance"));

        logger.info("Step 02: Call Retrieve Employee record for Health Insurance - API");
        var response = api.retrieveEmployeeRecord_api.retrieveEmployeeRecord(data);

        logger.info("Step 03: Validate response (status + fields)");
        api.retrieveEmployeeRecord_api.assertRetrieveEmployeeRecord(data, response);
    }

    @Test(dataProvider = "testDataProvider")
    public void retrieveEmployeeRecordWorkPermit(Map<String,String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data);

        logger.info("Step 01: Get retrieve employee record api for Work Permit from RunManager");
        data.putAll(api.retrieveEmployeeRecord_api.getAPIDetails("RetrieveEmployeeRecordWorkPermit"));

        logger.info("Step 02: Call Retrieve Employee record for Work Permit - API");
        var response = api.retrieveEmployeeRecord_api.retrieveEmployeeRecord(data);

        logger.info("Step 03: Validate response (status + fields)");
        api.retrieveEmployeeRecord_api.assertRetrieveEmployeeRecord(data, response);
    }

    @Test(dataProvider = "testDataProvider")
    public void viewEmployeeProfilePicture(Map<String,String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data);

        logger.info("Step 01: Get - View Employee Profile Picture - API details from RunManager");
        data.putAll(api.viewEmployeeProfilePicture_api.getAPIDetails("ViewEmployeeProfilePicture"));

        logger.info("Step 02: Call Retrieve Employee Details API");
        //Another way of calling
        //ApiResponse response = api.cafmApi.cafmCreateWorkOrder(data);
        //with JDK21
        var response = api.viewEmployeeProfilePicture_api.viewEmployeeProfilePicture(data);

        logger.info("Step 03: Validate response (status + fields)");
        api.viewEmployeeProfilePicture_api.assertViewEmployeeProfilePicture(data, response);


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
