package com.orangehrm.api.repo;

import com.testcrew.api.UnirestAPI;
import com.testcrew.manager.ReportManager;
import com.testcrew.manager.TestDataManager;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import java.util.Map;

public class LoginAPI extends UnirestAPI {
    public static final ReportManager logger = new ReportManager(LoginAPI.class);

    /**
     * API function
     * A API function is the one which performs the API request to the Application
     */
    public HttpResponse<JsonNode> login(Map<String, String> data) throws Exception {
        Map<String, String> apiData = TestDataManager.getTestDataAsMap("APIDetails", "Login");
        processRequestURL(apiData);
        // Payload Processing
        String payload = apiData.get("RequestPayload");
        payload = payload.replace("{username}", data.get("Username"));
        payload = payload.replace("{password}", data.get("Password"));
        apiData.put("RequestPayload", payload);
        return getResponse(apiData);
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

    /**
     * Assert function
     * A Assert function is the one which does the Test Validation & Reports the same in Report
     * Always use addPassLabel
     */
    public void assertLoginResponse(HttpResponse<JsonNode> response) {
        UnirestAPI.assertResponseCode(response, "200");
        UnirestAPI.assertResponseMessage(response, "");
    }
}
