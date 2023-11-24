package com.orangehrm.web;

import com.orangehrm.web.pages.*;
import com.testcrew.manager.TestConfigManager;
import com.testcrew.web.Browser;

import java.util.Map;

public class OrangeHRMWebApplication {

    public final LoginPage loginPage;
    public final SideMenuPage sideMenu;
    public final MyInfoPage myInfoPage;

    public OrangeHRMWebApplication() {
        loginPage = new LoginPage();
        sideMenu = new SideMenuPage();
        myInfoPage = new MyInfoPage();
    }

    public void openApplication(Map<String, String> data) throws Exception {
        if (data.get("URL") != null) {
            Browser.openUrl(data.get("URL"));
        } else {
            Browser.openUrl(TestConfigManager.getTestSettingsWebAppURL());
        }
        loginPage.waitForPageLoad();
    }
}
