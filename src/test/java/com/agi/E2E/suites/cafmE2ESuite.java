package com.agi.E2E.suites;

import com.agi.api.base.AgiAPITest;
import com.agi.api.repo.CafmAPI;
import com.agi.mobile.base.AgiMobileTest;
import com.agi.web.base.AgiWebTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import java.util.Map;

public class cafmE2ESuite extends AgiWebTest {

    private final CafmAPI cafmApi = new CafmAPI(); // <- composition

    // TC_MOB_1     //ZephyrID//AEA-T130
    @Test(dataProvider = "testDataProvider",
            description = "Scenario: Login with valid Driver ID on Mobile\n" +
                    "- Given a mobile user exists with a valid Driver ID\n" +
                    "- When the user logs in with correct credentials\n" +
                    "- Then the login should be successful\n" +
                    "- And the user dashboard should be displayed", priority = 1)
    public void cafmWOCreateVerifyWebUI(Map<String, String> data) throws Exception {
        //API Steps
        logger.info("Step 00: Test Data : " + data);
        logger.info("Step 01: Get CAFM API details from RunManager");
        data.putAll(cafmApi.getCafmWorkOrderAPIDetails("CreateWorkOrderCAFM"));
        logger.info("Step 02: Call CreateWorkOrder");
        var response = cafmApi.cafmCreateWorkOrder(data);
        logger.info("Step 03: Validate response (status + fields)");
        cafmApi.assertCafmCreateWorkOrderResponse(data, response);
        // ---- UI steps using AgiWebTest’s driver ----
        logger.info("Step 04: Test Data : " + data.toString());
        app.openApplication(data);
        Thread.sleep(2);
        logger.info("Step 01: Login to Application Enter Username, Enter Password, click Login");
        app.loginPage.enterCafmUsername(data.get("Username"));
        Thread.sleep(3);
        app.loginPage.enterCafmPassword(data.get("Password"));
        Thread.sleep(3);
        app.loginPage.clickCafmLogin();

/*        String workOrderId = data.get("WorkOrderId");
        loginPage.login(...);
        workOrdersPage.search(workOrderId);
        workOrdersPage.assertRowHas("WorkOrderId", workOrderId);*/

    }
}






