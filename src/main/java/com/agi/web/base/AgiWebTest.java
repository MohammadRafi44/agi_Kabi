package com.agi.web.base;

import com.agi.web.AgiWebApplication;
import com.alghurair.web.Browser;
import com.alghurair.wrapper.base.AlGhurairWebBaseTest;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

public class AgiWebTest extends AlGhurairWebBaseTest {

    public AgiWebApplication app;

    public AgiWebTest() {
        app = new AgiWebApplication();
    }

    @BeforeSuite
    public void beforeSuiteRewaa() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        Browser.setUserDesiredCapabilities(desiredCapabilities);
    }
}