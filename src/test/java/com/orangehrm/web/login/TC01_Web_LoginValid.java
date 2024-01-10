package com.orangehrm.web.login;

import com.orangehrm.web.base.OrangeHRMWebTest;
import com.testcrew.manager.TestDataManager;
import org.testng.annotations.Test;

import java.util.Map;

public class TC01_Web_LoginValid extends OrangeHRMWebTest {

    @Test(dataProvider = "testDataProvider")
    public void loginValid(Map<String, String> data) throws Exception {
        logger.info("Step 00: Test Data : " + data.toString());
        app.openApplication(data);

        TestDataManager.addDependantTestData("loginInvalid", "Key1", "Value1");
        TestDataManager.addDependantTestData("loginInvalid", "Key2", "Value2");
        TestDataManager.writeDependantTestData("loginInvalid");

//        app.loginPage.assertLoginPageDisplayed();
//        logger.info("Step 01: Login to Application Enter Username, Enter Password, click Login");
//        app.loginPage.enterUsername(data.get("Username"));
//        app.loginPage.enterPassword(data.get("Password"));
//        app.loginPage.clickLogin();
//
//        logger.info("Step 02: Assert Side Menu 'My Info' displayed.");
//        app.sideMenu.assertMenuExist("My Info");
//
//        logger.info("Step 03: Open My Info Page.");
//        app.sideMenu.openMenuPage("My Info");
//        app.myInfoPage.waitForPageLoad();
//
//        logger.info("Step 04: Assert My Info Page displayed.");
//        app.myInfoPage.assertMyInfoPageDisplayed();
//
//        logger.info("Step 05: Enter/Select the following General Firstname, MiddleName, LastName");
//        app.myInfoPage.enterFirstName(data.get("FirstName"));
//        app.myInfoPage.enterMiddleName(data.get("MiddleName"));
//        app.myInfoPage.enterLastName(data.get("LastName"));
//
//        logger.info("Step 06: Click the Save button");
//        app.myInfoPage.clickSave();
//
//        logger.info("Step 07: Assert Success Message displayed");
//        app.myInfoPage.assertSuccessMessageDisplayed();
    }
}
