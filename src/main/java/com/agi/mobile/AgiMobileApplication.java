package com.agi.mobile;

import com.agi.mobile.pages.*;

import java.util.Map;

public class AgiMobileApplication {

    public final HomePage homePage;
    public final LoginPage loginPage;

    public AgiMobileApplication() {
        homePage = new HomePage();
        loginPage = new LoginPage();
    }
}
