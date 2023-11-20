package com.orangehrm.web.login;

import com.orangehrm.web.base.OrangeHRMWebTest;
import com.testcrew.manager.TestConfigManager;
import org.testng.annotations.Test;

import java.util.Map;

public class TC01_LoginValid extends OrangeHRMWebTest {

    @Test(dataProvider = "testDataProvider")
    public void loginValid(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data.toString());
        app.openApplication(data);
        app.loginPage.assertLoginPageDisplayed();
        logger.info("Step 01: Login to Application Enter Username, Enter Password, click Login");
        app.loginPage.enterUsername(data.get("Username"));
        app.loginPage.enterPassword(data.get("Password"));
        app.loginPage.clickLogin();
        logger.info("Step 02: Select 'Simple Product' option");
        logger.info("Step 03: Enter the following Basic Information: Product Name, SKU (Auto generate), Barcode, Product Description");
        logger.info("Step 04/05: Click the 'Add Image' button Select image from the option");
        logger.info("Step 06: Enter/Select the following General Information: Category, Supplier, Brand");
        logger.info("Step 07: Enter the following Shipping Information: Shipping weight, Width, Length, Height");
        logger.info("Step 08: Enter the following Product stock and pricing information:, Initial Quantity, Retail Price, Wholesale Price, Buy Price, Initial Cost, Tax Type");
        logger.info("Step 09: Click the Save button");
        logger.info("Step 10: Validate Newly created product is displayed in the inventory list");
    }
}
