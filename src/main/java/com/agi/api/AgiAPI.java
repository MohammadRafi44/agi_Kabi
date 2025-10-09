package com.agi.api;

import com.agi.api.repo.*;
//import com.agi.api.repo.LoginAPI;

public class AgiAPI {

//    public final LoginAPI loginApi;
    public final CafmAPI cafmApi;
    public final AgfsRelyApp relyApp;
    public final RetrieveEmployeeDetails_API retrieveEmployeeDetailsApi;
    public final RetrieveEmergencyContactsDetail_API retrieveEmergencyContactsDetailApi;
    public final RetrieveEmployeeRecord_API retrieveEmployeeRecord_api;
    public final ViewEmployeeProfilePicture_API viewEmployeeProfilePicture_api;


    public AgiAPI() {
//        loginApi = new LoginAPI();
        cafmApi = new CafmAPI();
        relyApp = new AgfsRelyApp();
        retrieveEmployeeDetailsApi = new RetrieveEmployeeDetails_API();
        retrieveEmergencyContactsDetailApi = new RetrieveEmergencyContactsDetail_API();
        retrieveEmployeeRecord_api = new RetrieveEmployeeRecord_API();
        viewEmployeeProfilePicture_api = new ViewEmployeeProfilePicture_API();




    }
}