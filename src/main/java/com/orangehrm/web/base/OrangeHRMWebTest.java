package com.orangehrm.web.base;

import com.orangehrm.web.OrangeHRMWebApplication;
import com.testcrew.web.Browser;
import com.testcrew.wrapper.base.TestCrewWebBaseTest;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

public class OrangeHRMWebTest extends TestCrewWebBaseTest {

    public OrangeHRMWebApplication app;

    public OrangeHRMWebTest() {
        app = new OrangeHRMWebApplication();
    }

    @BeforeSuite
    public void beforeSuiteRewaa() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        Browser.setUserDesiredCapabilities(desiredCapabilities);
    }
}