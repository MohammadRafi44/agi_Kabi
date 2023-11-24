package com.orangehrm.mobile.login;

import com.orangehrm.mobile.base.OrangeHRMMobileTest;
import com.testcrew.manager.TestConfigManager;
import org.testng.annotations.Test;

import java.util.Map;

public class TC01_Mobile_LoginValid extends OrangeHRMMobileTest {

    @Test(dataProvider = "testDataProvider")
    public void mobileLoginValid(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data.toString());
        app.homePage.waitForPageLoad();
        logger.info("Step 01: Enter Instance URL & Continue");
        app.homePage.enterInstanceURL(TestConfigManager.getTestSettingsWebAppURL());

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
