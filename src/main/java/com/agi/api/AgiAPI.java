package com.agi.api;

import com.agi.api.repo.AgfsRelyApp;
import com.agi.api.repo.CafmAPI;
//import com.agi.api.repo.LoginAPI;

public class AgiAPI {

//    public final LoginAPI loginApi;
    public final CafmAPI cafmApi;
    public final AgfsRelyApp relyApp;


    public AgiAPI() {
//        loginApi = new LoginAPI();
        cafmApi = new CafmAPI();
        relyApp = new AgfsRelyApp();




    }
}