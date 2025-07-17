package com.agi.web.login;

import com.agi.web.base.AgiWebTest;
import com.alghurair.manager.TestDataManager;
import org.testng.annotations.Test;

import java.util.Map;

public class TC01_Web_LoginValid extends AgiWebTest {

    @Test(dataProvider = "testDataProvider")
    public void loginValid(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data.toString());
        app.openApplication(data);
        app.loginPage.assertLoginPageDisplayed();
        logger.info("Step 01: Login to Application Enter Username, Enter Password, click Login");
        app.loginPage.enterUsername(data.get("Username"));
        app.loginPage.enterPassword(data.get("Password"));
        app.loginPage.clickLogin();

        logger.info("Step : Assert Side Menu 'My Info' displayed.");
//        logger.info("Step 02: Assert Side Menu 'My Info' displayed.");
        app.sideMenu.assertMenuExist("My Info");
        logger.info("Step: Open My Info Page.");
        app.sideMenu.openMenuPage("My Info");
        app.myInfoPage.waitForPageLoad();

        logger.info("Step 04: Assert My Info Page displayed.");
        app.myInfoPage.assertMyInfoPageDisplayed();
        logger.info("Step 05: Enter/Select the following General Firstname, MiddleName, LastName");
        app.myInfoPage.enterFirstName(data.get("FirstName"));
        app.myInfoPage.enterMiddleName(data.get("MiddleName"));
        app.myInfoPage.enterLastName(data.get("LastName"));

        logger.info("Step 06: Click the Save button");
        app.myInfoPage.clickSave();

        logger.info("Step 07: Assert Success Message displayed");
        app.myInfoPage.assertSuccessMessageDisplayed();

        TestDataManager.addDependantTestData("test2", "TransactionID", "TransactionID");
        TestDataManager.addDependantTestData("test2", "Username", "Username");
        TestDataManager.writeDependantTestData("test2");

        TestDataManager.addDependantGlobalTestData("Global", "GlobalTransactionID", "GlobalTransactionID");
        TestDataManager.addDependantGlobalTestData("Global", "GlobalUsername", "GlobalUsername");
        TestDataManager.writeDependantGlobalTestData("Global");
    }
}
