package com.agi.mobile.objects;

import com.alghurair.manager.TestConfigManager;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class HomePageObjects {

    static Map<String, Map<String, String>> objects = new HashMap<>() {{
        // Button
        put("button-Continue", new HashMap<>() {{
            put("en", "//android.widget.TextView[@text='CONTINUE']");
            put("ar", "");
        }});
        // Checkbox
        // Dropdown
        // Element
        // Label
        put("label-Enter-Agi-URL", new HashMap<>() {{
            put("en", "//android.widget.TextView[@text='Enter OrangeHRM URL']");
            put("ar", "");
        }});
        // Link
        // Radio
        // Text
        put("text-Instance-URL", new HashMap<>() {{
            put("en", "//android.widget.EditText");
            put("ar", "");
        }});
    }};

    public static String get(String locator) throws Exception {
        return objects.get(locator).get(TestConfigManager.getTestSettingsMobileAppLanguage());
    }

    // Button
    public static By getButtonContinue() throws Exception {
        return By.xpath(get("button-Continue"));
    }

    // Checkbox
    // Dropdown
    // Element
    // Label
    public static By getLabelEnterAgiURL() throws Exception {
        return By.xpath(get("label-Enter-Agi-URL"));
    }

    // Link
    // Radio
    // Text
    public static By getTextInstanceURL() throws Exception {
        return By.xpath(get("text-Instance-URL"));
    }
}
