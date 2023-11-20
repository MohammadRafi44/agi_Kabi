package com.orangehrm.web.objects;

import com.testcrew.manager.TestConfigManager;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class LoginPageObjects {
    /**
     * Page Objects
     * A map of map is used to store the page objects of the Application
     * Add objects in the Right section
     * Add getters to get the Page Objects.
     * Name the method with the type of the object, following standards
     */

    static Map<String, Map<String, String>> objects = new HashMap<>() {{
        // Button
        put("button-language", new HashMap<>() {{
            put("en", "//button[@label='Eng']");
            put("ar", "//button[@label='العربية']");
        }});
        put("button-login", new HashMap<>() {{
            put("en", "//button[contains(@type,'submit')]");
            put("ar", "//button//span[normalize-space(text())='تسجيل الدخول']");
        }});
        // Checkbox
        // Dropdown
        // Element
        put("element-error-invalid-credentials", new HashMap<>() {{
            put("en", "//p[(text() = 'Invalid credentials' or . = 'Invalid credentials')]");
            put("ar", "//p[(text() = 'Invalid credentials' or . = 'Invalid credentials')]");
        }});

        // Link
        // Radio
        // Text
        put("text-username", new HashMap<>() {{
            put("en", "//input[@name='username']");
            put("ar", "//input[@name='username']");
        }});
        put("text-password", new HashMap<>() {{
            put("en", "//input[@name='password']");
            put("ar", "//input[@name='password']");
        }});
    }};

    public static String get(String locator) throws Exception {
        return objects.get(locator).get(TestConfigManager.getSettingsApplicationLanguage());
    }

    public static String get(String locator, String language) throws Exception {
        return objects.get(locator).get(language);
    }

    // Button
    public static By getButtonLanguage(String language) throws Exception {
        return By.xpath(get("button-language", language));
    }

    public static By getButtonLogin() throws Exception {
        return By.xpath(get("button-login"));
    }

    // Checkbox
    // Dropdown
    // Element
    public static By getElementErrorInvalidCredentials() throws Exception {
        return By.xpath(get("element-error-invalid-credentials"));
    }

    // Link
    // Radio
    // Text
    public static By getTextUsername() throws Exception {
        return By.xpath(get("text-username"));
    }

    public static By getTextPassword() throws Exception {
        return By.xpath(get("text-password"));
    }
}
