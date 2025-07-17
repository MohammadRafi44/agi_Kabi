package com.agi.web.objects;

import com.alghurair.manager.TestConfigManager;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class SideMenuPageObjects {
    /**
     * Page Objects
     * A map of map is used to store the page objects of the Application
     * Add objects in the Right section
     * Add getters to get the Page Objects.
     * Name the method with the type of the object, following standards
     */

    static Map<String, Map<String, String>> objects = new HashMap<>() {{
        // Button
        // Checkbox
        // Dropdown
        // Element
        // Link
        // Menu
        put("menu", new HashMap<>() {{
            put("en", "//a[contains(@class,'main-menu-item')]/span[text()='{menu}']");
            put("ar", "//a[contains(@class,'main-menu-item')]/span[text()='{menu}']");
        }});
        put("menu-active", new HashMap<>() {{
            put("en", "//a[contains(@class,'main-menu-item') and contains(@class,'active')]/span[text()='{menu}']");
            put("ar", "//a[contains(@class,'main-menu-item') and contains(@class,'active')]/span[text()='{menu}']");
        }});
        // Radio
        // Text
    }};

    public static String get(String locator) throws Exception {
        return objects.get(locator).get(TestConfigManager.getSettingsApplicationLanguage());
    }

    public static String get(String locator, String language) throws Exception {
        return objects.get(locator).get(language);
    }

    // Button
    // Checkbox
    // Dropdown
    // Element
    // Link
    // Menu
    public static By getMenu(String menu) throws Exception {
        return By.xpath(get("menu").replace("{menu}", menu));
    }
    public static By getMenuActive(String menu) throws Exception {
        return By.xpath(get("menu-active").replace("{menu}", menu));
    }
    // Radio
    // Text
}
