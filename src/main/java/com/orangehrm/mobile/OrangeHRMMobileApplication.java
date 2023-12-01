package com.orangehrm.mobile;

import com.orangehrm.mobile.pages.*;

import java.util.Map;

public class OrangeHRMMobileApplication {

    public final HomePage homePage;
    public final LoginPage loginPage;

    public OrangeHRMMobileApplication() {
        homePage = new HomePage();
        loginPage = new LoginPage();
    }
}
