package com.agi.mobile.base;

import com.agi.mobile.AgiMobileApplication;
import com.alghurair.manager.TestConfigManager;
import com.alghurair.mobile.Mobile;
import com.alghurair.wrapper.base.AlGhurairMobileBaseTest;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

public class AgiMobileTest extends AlGhurairMobileBaseTest {

    public AgiMobileApplication app;

    public AgiMobileTest() {
        app = new AgiMobileApplication();
    }

    @BeforeSuite
    public void beforeSuiteRewaa() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        // Browser Stack Capabilities
        if (TestConfigManager.getTestSettingsMobileDeviceType().equalsIgnoreCase("browserstack")) {
            desiredCapabilities.setCapability("app", TestConfigManager.getTestSettingsMobileBrowserStackAppURL());
            desiredCapabilities.setCapability("project", TestConfigManager.getTestSettingsMobileBrowserStackProject());
            desiredCapabilities.setCapability("build", TestConfigManager.getTestSettingsMobileBrowserStackBuild());
            desiredCapabilities.setCapability("name", "Test");
        }

        desiredCapabilities.setCapability("deviceName", TestConfigManager.getTestSettingsMobileDeviceName());
        desiredCapabilities.setCapability("platformName", TestConfigManager.getTestSettingsMobilePlatform());

        //In Appium v2, you must explicitly specify the automationName capability. It's not optional anymore.
        desiredCapabilities.setCapability("automationName", "UiAutomator2");

        desiredCapabilities.setCapability("autoGrantPermissions", "true");
        desiredCapabilities.setCapability("appPackage", TestConfigManager.getTestSettingsMobileAppPackage());
        desiredCapabilities.setCapability("appActivity", TestConfigManager.getTestSettingsMobileAppActivity());
        desiredCapabilities.setCapability("newCommandTimeout", 100000);
//        desiredCapabilities.setCapability("noReset", "true");
        desiredCapabilities.setCapability("fullReset", "false");
        desiredCapabilities.setCapability("autoAcceptAlerts", "true");
        desiredCapabilities.setCapability("autoDismissAlerts", "true");
        desiredCapabilities.setCapability("autoGrantPermissions", "true");
        desiredCapabilities.setCapability("unicodeKeyboard", "true");
        desiredCapabilities.setCapability("resetKeyboard", "true");

//        desiredCapabilities.setCapability("autoWebview", "true");
//        desiredCapabilities.setCapability("autoWebviewTimeout", "20000");
//        desiredCapabilities.setCapability("chromedriverExecutableDir", "/usr/local/bin/chromedriver");
//        desiredCapabilities.setCapability("chromedriverChromeMappingFile", "/usr/local/bin/chromedriver-chromium-version-mapping.json");


//            if (data.get("AutomationName").equalsIgnoreCase("uiautomator2")) {
//                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, ANDROID_UIAUTOMATOR2);
//            }
//        } else if (data.get("PlatformName").equalsIgnoreCase("ios")) {
//            desiredCapabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, Platform.IOS);
//        }
//        desiredCapabilities.setCapability("autoGrantPermissions", data.get("AutoGrantPermissions"));

        Mobile.setUserDesiredCapabilities(desiredCapabilities);
    }

}