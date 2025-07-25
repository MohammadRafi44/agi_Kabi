package com.agi.mobile.objects;

import com.alghurair.manager.TestConfigManager;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class MyProfilePageObjects {

    static Map<String, Map<String, String>> objects = new HashMap<>() {{
        // Button

        put("button-Logout", new HashMap<>() {{
            put("en", "//android.widget.TextView[@text=\"Log Out\"]");
            put("ar", "//android.widget.TextView[@text=\"Log Out\"]");
        }});

        put("button-Yes", new HashMap<>() {{
            put("en", "//android.widget.TextView[@text=\"Yes\"]");
            put("ar", "//android.widget.TextView[@text=\"Yes\"]");
        }});

        // Checkbox
        // Dropdown
        // Element
        // Label


        // Link
        // Radio
        // Text


    }};

    public static String get(String locator) throws Exception {
        return objects.get(locator).get(TestConfigManager.getTestSettingsMobileAppLanguage());
    }

    // Button


    public static By getButtonLogout() throws Exception {
        return By.xpath(get("button-Logout"));
    }

    public static By getButtonYes() throws Exception {
        return By.xpath(get("button-Yes"));
    }

    // Checkbox
    // Dropdown
    // Element
    // Label



    // Link
    // Radio
    // Text


}
