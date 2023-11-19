package com.orangehrm.api;

import com.orangehrm.api.repo.LoginAPI;

public class OrangeHRMAPI {

    public final LoginAPI loginApi;

    public OrangeHRMAPI() {
        loginApi = new LoginAPI();
    }
}