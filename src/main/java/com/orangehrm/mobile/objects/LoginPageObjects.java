package com.orangehrm.mobile.objects;

import com.alghurair.manager.TestConfigManager;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class LoginPageObjects {

    static Map<String, Map<String, String>> objects = new HashMap<>() {{
        // Button
        put("button-Login", new HashMap<>() {{
            put("en", "//android.widget.Button[@text='Login']");
            put("ar", "");
        }});
        // Checkbox
        // Dropdown
        // Element
        // Label
        put("label-Login", new HashMap<>() {{
            put("en", "//android.widget.TextView[@text='Login']");
            put("ar", "");
        }});
        put("label-Invalid credentials", new HashMap<>() {{
            put("en", "//android.widget.TextView[@text='Invalid credentials']");
            put("ar", "");
        }});
        // Link
        // Radio
        // Text
        put("text-Username", new HashMap<>() {{
            put("en", "(//android.widget.EditText)[1]");
            put("ar", "");
        }});
        put("text-Password", new HashMap<>() {{
            put("en", "(//android.widget.EditText)[2]");
            put("ar", "");
        }});
    }};

    public static String get(String locator) throws Exception {
        return objects.get(locator).get(TestConfigManager.getTestSettingsMobileAppLanguage());
    }

    // Button
    public static By getButtonLogin() throws Exception {
        return By.xpath(get("button-Login"));
    }

    // Checkbox
    // Dropdown
    // Element
    // Label
    public static By getLabelLogin() throws Exception {
        return By.xpath(get("label-Login"));
    }

    public static By getLabelErrorInvalidCredentials() throws Exception {
        return By.xpath(get("label-Invalid credentials"));
    }

    // Link
    // Radio
    // Text
    public static By getTextUsername() throws Exception {
        return By.xpath(get("text-Username"));
    }

    public static By getTextPassword() throws Exception {
        return By.xpath(get("text-Password"));
    }
}
