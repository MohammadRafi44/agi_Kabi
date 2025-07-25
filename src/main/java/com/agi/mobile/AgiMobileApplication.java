package com.agi.mobile;

import com.agi.mobile.pages.*;

import java.util.Map;

public class AgiMobileApplication {

    public final HomePage homePage;
    public final LoginPage loginPage;
    public final MenuOptions menuOptions;
    public final MyProfilePage myProfilePage;


    public AgiMobileApplication() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        menuOptions = new MenuOptions();
        myProfilePage = new MyProfilePage();

    }
}
