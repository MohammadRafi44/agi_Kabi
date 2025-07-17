package com.agi.web.objects;

import com.alghurair.manager.TestConfigManager;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class MyInfoPageObjects {
    /**
     * Page Objects
     * A map of map is used to store the page objects of the Application
     * Add objects in the Right section
     * Add getters to get the Page Objects.
     * Name the method with the type of the object, following standards
     */

    static Map<String, Map<String, String>> objects = new HashMap<>() {{
        // Button
        put("button-save", new HashMap<>() {{
            put("en", "//button[@type='submit']");
            put("ar", "//button[@type='submit']");
        }});
        // Checkbox
        // Dropdown
        // Element
        put("element-personal-details", new HashMap<>() {{
            put("en", "//h6[(text() = 'Personal Details' or . = 'Personal Details')]");
            put("ar", "//a[contains(@class,'main-menu-item')]/span[text()='{menu}']");
        }});
        put("element-success", new HashMap<>() {{
            put("en", "//p[text()='Success']");
            put("ar", "//p[text()='Success']");
        }});
        // Link
        // Radio
        // Text
        put("text-first-name", new HashMap<>() {{
            put("en", "//input[@name='firstName']");
            put("ar", "//input[@name='firstName']");
        }});
        put("text-middle-name", new HashMap<>() {{
            put("en", "//input[@name='middleName']");
            put("ar", "//input[@name='middleName']");
        }});
        put("text-last-name", new HashMap<>() {{
            put("en", "//input[@name='lastName']");
            put("ar", "//input[@name='lastName']");
        }});

    }};

    public static String get(String locator) throws Exception {
        return objects.get(locator).get(TestConfigManager.getSettingsApplicationLanguage());
    }

    public static String get(String locator, String language) throws Exception {
        return objects.get(locator).get(language);
    }

    // Button
    public static By getButtonSave() throws Exception {
        return By.xpath(get("button-save"));
    }

    // Checkbox
    // Dropdown
    // Element
    public static By getElementPersonalDetails() throws Exception {
        return By.xpath(get("element-personal-details"));
    }
    public static By getElementSuccess() throws Exception {
        return By.xpath(get("element-success"));
    }
    // Link
    // Radio
    // Text
    public static By getTextFirstName() throws Exception {
        return By.xpath(get("text-first-name"));
    }

    public static By getTextMiddleName() throws Exception {
        return By.xpath(get("text-middle-name"));
    }

    public static By getTextLastName() throws Exception {
        return By.xpath(get("text-last-name"));
    }
}
