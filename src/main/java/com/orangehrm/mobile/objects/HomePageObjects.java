package com.orangehrm.mobile.objects;

import com.testcrew.manager.TestConfigManager;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class HomePageObjects {

    static Map<String, Map<String, String>> objects = new HashMap<>() {{
        // Button
        put("button-get-started", new HashMap<>() {{
            put("en", "//*[@class = 'android.widget.TextView' and (@text = 'Get Started!' or . = 'Get Started!')]");
            put("ar", "");
        }});
        // Checkbox
        // Dropdown
        // Element
        // Label
        put("label-Enter-OrangeHRM-URL", new HashMap<>() {{
            put("en", "//*[@class = 'android.widget.TextView' and (@text = 'Enter OrangeHRM URL' or . = 'Enter OrangeHRM URL')]");
            put("ar", "");
        }});
        // Link
        // Radio
        // Text
        put("text-Instance-URL", new HashMap<>() {{
            put("en", "(//*[@class='android.widget.EditText'])[2]");
            put("ar", "");
        }});
    }};

    public static String get(String locator) throws Exception {
        return objects.get(locator).get(TestConfigManager.getTestSettingsMobileAppLanguage());
    }

    // Button
    public static By getButtonGetStarted() throws Exception {
        return By.xpath(get("button-get-started"));
    }

    // Checkbox
    // Dropdown
    // Element
    // Label
    public static By getLabelEnterOrangeHRMURL() throws Exception {
        return By.xpath(get("label-Enter-OrangeHRM-URL"));
    }

    // Link
    // Radio
    // Text
    public static By getTextInstanceURL() throws Exception {
        return By.xpath(get("text-Instance-URL"));
    }
}
