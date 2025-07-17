package com.agi.api;

import com.agi.api.repo.LoginAPI;

public class AgiAPI {

    public final LoginAPI loginApi;

    public AgiAPI() {
        loginApi = new LoginAPI();
    }
}