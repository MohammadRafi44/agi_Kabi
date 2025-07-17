package com.agi.web;

import com.agi.web.pages.*;
import com.alghurair.manager.TestConfigManager;
import com.alghurair.web.Browser;

import java.util.Map;

public class AgiWebApplication {

    public final LoginPage loginPage;
    public final SideMenuPage sideMenu;
    public final MyInfoPage myInfoPage;

    public AgiWebApplication() {
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
//        loginPage.waitForPageLoad();
    }
}
