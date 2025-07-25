package com.agi.mobile.objects;

import com.alghurair.manager.TestConfigManager;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class LoginPageObjects {

    static Map<String, Map<String, String>> objects = new HashMap<>() {{
        // Button
        put("button-Login", new HashMap<>() {{
            put("en", "//android.widget.TextView[@text=\"Log In\"]");
            put("ar", "//android.widget.TextView[@text=\"Log In\"]");
        }});
        put("btn-UAT", new HashMap<>() {{
            put("en", "//android.widget.TextView[@text='UAT']");
            put("ar", "//android.widget.TextView[@text='UAT']");
        }});
        put("button-GenerateOtp", new HashMap<>() {{
            put("en", "(//android.view.ViewGroup[@content-desc=\"Generate OTP\"])");
            put("ar", "(//android.view.ViewGroup[@content-desc=\"Generate OTP\"])");
        }});
        put("button-Continue", new HashMap<>() {{
            put("en", "(//android.widget.TextView[@text=\"Continue\"]\n)");
            put("ar", "(//android.widget.TextView[@text=\"Continue\"]\n)");
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
        put("label-hellothere", new HashMap<>() {{
            put("en", "(//android.widget.TextView[@text=\" Hello there!\"])");
            put("ar", "(//android.widget.TextView[@text=\" Hello there!\"])");
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

        put("text-Driverid", new HashMap<>() {{
            put("en", "(//android.widget.EditText)[1]");
            put("ar", "(//android.widget.EditText)[1]");
        }});

        put("text-OtpDigit0", new HashMap<>() {{
            put("en", "(//android.widget.EditText)[1]");
            put("ar", "(//android.widget.EditText)[1]");
        }});
        put("text-OtpDigit1", new HashMap<>() {{
            put("en", "(//android.widget.EditText)[2]");
            put("ar", "(//android.widget.EditText)[2]");
        }});
        put("text-OtpDigit2", new HashMap<>() {{
            put("en", "(//android.widget.EditText)[3]");
            put("ar", "(//android.widget.EditText)[3]");
        }});
        put("text-OtpDigit3", new HashMap<>() {{
            put("en", "(//android.widget.EditText)[4]");
            put("ar", "(//android.widget.EditText)[4]");
        }});
        put("text-OtpDigit4", new HashMap<>() {{
            put("en", "(//android.widget.EditText)[5]");
            put("ar", "(//android.widget.EditText)[5]");
        }});
        put("text-OtpDigit5", new HashMap<>() {{
            put("en", "(//android.widget.EditText)[6]");
            put("ar", "(//android.widget.EditText)[6]");
        }});

        //welcome text - Welcome to the new Buddy’s app!
        put("text-Welcome", new HashMap<>() {{
            put("en", "//android.widget.TextView[@text=\"Welcome to the new Buddy’s app!\"]");
            put("ar", "//android.widget.TextView[@text=\"Welcome to the new Buddy’s app!\"]");
        }});

        put("text-HomePage", new HashMap<>() {{
            put("en", "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView");
            put("ar", "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView");
        }});



    }};

    public static String get(String locator) throws Exception {
        return objects.get(locator).get(TestConfigManager.getTestSettingsMobileAppLanguage());
    }

    // Button
    public static By getButtonLogin() throws Exception {
        return By.xpath(get("button-Login"));
    }
    public static By getButtonContinue() throws Exception {
        return By.xpath(get("button-Continue"));
    }

    // Checkbox
    // Dropdown
    // Element
    // Label
    public static By getLabelLogin() throws Exception {
        return By.xpath(get("label-Login"));
    }
    public static By getBtnUAT() throws Exception {
        return By.xpath(get("btn-UAT"));
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
    public static By getTextDriverID() throws Exception {
        return By.xpath(get("text-Driverid"));
    }
    public static By getButtonGenerateOtp() throws Exception {
        return By.xpath(get("button-GenerateOtp"));
    }
    public static By getLabelHelloThere() throws Exception {
        return By.xpath(get("label-hellothere"));
    }
    public static By getOtpDigit(int index) throws Exception {
        return By.xpath(get("text-OtpDigit" + index));
    }
    //text-Welcome Web Element
    public static By getTextWelcome() throws Exception {
        return By.xpath(get("text-Welcome"));
    }
    public static By getTextHomePage() throws Exception {
        return By.xpath(get("text-HomePage"));
    }
}
