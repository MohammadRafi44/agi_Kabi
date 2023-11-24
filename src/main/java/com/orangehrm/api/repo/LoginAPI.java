package com.orangehrm.api.repo;

import com.testcrew.api.UnirestAPI;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;

import java.util.Map;

public class LoginAPI extends UnirestAPI {

    /**
     * Getter function
     * A Getter function is the one which fetch the API request & expected response details
     */
    public Map<String, String> getLoginAPIDetails(String reference) throws Exception {
        return getAPIDetails(reference);
    }

    /**
     * API function
     * A API function is the one which performs the API request to the Application
     */
    public HttpResponse<JsonNode> login(Map<String, String> data) throws Exception {
        // Header construct
        // TODO implement

        // Payload construct
        String payload = data.get("RequestPayload");
        payload = payload.replace("{username}", data.get("Username"));
        payload = payload.replace("{password}", data.get("Password"));
        data.put("RequestPayload", payload);

        // API Call
        HttpResponse<JsonNode> response = getResponse(data);

        // Extract API Response required data
        JSONObject obj = new JSONObject(response.getBody().toString());
        if (obj.has("token")) {
            data.put("api_token", obj.getString("token"));
        }
        return response;
    }

    /**
     * Wait function
     * A Wait function is the one which waits for certain event on the API response.
     */
    public void waitForAccountStatus() throws Exception {
    }

    /**
     * Verify function
     * A Verify function is the one which return a boolean flag as a rust of the verification
     * Can be used in the Asserts
     * Can be used for conditional business flow based on data.
     */
    public void isTokenExist() throws Exception {
    }

    /**
     * Assert function
     * A Assert function is the one which does the Test Validation & Reports the same in Report
     * Always use addPassLabel
     */
    public void assertLoginResponse(Map<String, String> data) {
        // Response payload construct
        String expectedPayload = data.get("ExpectedResponsePayload");

        if (expectedPayload.contains("{api_token}")) {
            expectedPayload = expectedPayload.replace("{api_token}", data.get("api_token"));
        }
        data.put("ExpectedResponsePayload", expectedPayload);
        UnirestAPI.assertAPIResponseStatusCode(data);
        UnirestAPI.assertAPIResponseStatusMessage(data);
        UnirestAPI.assertAPIResponsePayload(data);
    }
}
