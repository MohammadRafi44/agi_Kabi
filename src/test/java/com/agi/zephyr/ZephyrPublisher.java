package com.agi.zephyr;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class ZephyrPublisher {

    // Zephyr Scale Cloud v2 base
    private static final String BASE = "https://api.zephyrscale.smartbear.com/v2";

    // Args/env
    private static String bearerToken;                                // --token or env ZEPHYR_TOKEN (Jira API token)
    private static String projectKey;                                 // --projectKey or env PROJECT_KEY (e.g., "AEA")
    private static String resultsPath = "test-output/testng-results.xml";   // --results
    private static String mappingPath = "zephyr-integration/mapping.json";  // --mapping
    private static String cyclePrefix = "Daily Regression - ";              // --cyclePrefix

    // New: cycle naming options
    private static String cycleNameOverride = null;                   // --cycleName "<exact name>"
    private static String timestampFormat   = "ddMMyyyyHHmmss";       // --timestampFormat "<pattern>"
    private static boolean includeTimestamp = true;                   // --noStamp to disable

    public static void main(String[] args) {
        try {
            parseArgs(args);
            require("token", bearerToken);
            require("projectKey", projectKey);

            // Build cycle name
            String cycleName = resolveCycleName();
            log("Cycle: " + cycleName);

            // 1) Create cycle -> get KEY (and log id+key)
            String cycleKey = createCycle(cycleName, projectKey);
            if (cycleKey == null || cycleKey.isBlank()) {
                throw new RuntimeException("Failed to create cycle (no key returned)");
            }
            log("cycleKey=" + cycleKey);

            // 2) Load mapping + parse TestNG
            JSONObject mapping = readJson(mappingPath);
            JSONArray results = new TestNGParser().parse(resultsPath);

            // 3) Create executions with statusName
            for (int i = 0; i < results.length(); i++) {
                JSONObject r = results.getJSONObject(i);
                String lookup = r.optString("className");
                String status  = r.optString("status");
                if (lookup.isBlank() || status.isBlank()) continue;

                String testCaseKey = mapping.optString(lookup);
                if (testCaseKey == null || testCaseKey.isBlank()) {
                    log("[SKIP] No mapping for " + lookup);
                    continue;
                }

                String statusName = "PASS".equalsIgnoreCase(status) ? "Pass" : "Fail"; // Scale expects names
                String execId = createExecution(projectKey, testCaseKey, cycleKey, statusName);
                log("Created execution " + execId + " for " + testCaseKey + " -> " + statusName);
            }

            log("Done.");
        } catch (Exception e) {
            System.err.println("[ZephyrPublisher][ERROR] " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    // ---- cycle name builder ----
    private static String resolveCycleName() {
        if (cycleNameOverride != null && !cycleNameOverride.isBlank()) {
            return cycleNameOverride.trim();
        }
        String datePart = new SimpleDateFormat("dd MMM").format(new Date());
        if (includeTimestamp) {
            String stamp = new SimpleDateFormat(timestampFormat).format(new Date());
            return String.format("%s%s - %s", cyclePrefix, datePart, stamp);
        } else {
            return cyclePrefix + datePart;
        }
    }

    // ---- API calls (Zephyr Scale v2) ----

    /** Create a test cycle and return the cycle KEY (also logs ID). */
    private static String createCycle(String name, String projectKey) throws IOException {
        JSONObject body = new JSONObject()
                .put("name", name)
                .put("projectKey", projectKey);

        String resp = post(BASE + "/testcycles", body.toString());
        JSONObject obj = new JSONObject(resp);

        // Log both for visibility
        String id  = obj.optString("id", null);
        String key = obj.optString("key", null);
        log("cycleId=" + id + ", cycleKey=" + key);
        return key; // Use KEY in subsequent calls
    }

    /** Create a test execution inside the given cycleKey, with statusName ("Pass"/"Fail"). Returns execution ID. */
    private static String createExecution(String projectKey, String testCaseKey, String cycleKey, String statusName) throws IOException {
        JSONObject body = new JSONObject()
                .put("projectKey", projectKey)
                .put("testCaseKey", testCaseKey)
                .put("testCycleKey", cycleKey)   // IMPORTANT: key, not id
                .put("statusName", statusName);  // Provide status at creation

        String resp = post(BASE + "/testexecutions", body.toString());
        return new JSONObject(resp).optString("id", null);
    }

    // ---- HTTP helpers ----

    private static String post(String url, String body) throws IOException {
        HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
        c.setRequestMethod("POST");
        c.setDoOutput(true);
        setHeaders(c);
        try (OutputStream os = c.getOutputStream()) {
            os.write(body.getBytes(StandardCharsets.UTF_8));
        }
        return drain(c);
    }

    private static void setHeaders(HttpURLConnection c) {
        c.setRequestProperty("Authorization", "Bearer " + bearerToken);
        c.setRequestProperty("Content-Type", "application/json");
    }

    private static String drain(HttpURLConnection c) throws IOException {
        int code = c.getResponseCode();
        InputStream is = (code >= 200 && code < 300) ? c.getInputStream() : c.getErrorStream();
        String text = (is == null) ? "" : new String(is.readAllBytes(), StandardCharsets.UTF_8);
        if (code < 200 || code >= 300) {
            throw new IOException(c.getRequestMethod() + " " + c.getURL() + " -> " + code + " :: " + text);
        }
        return text;
    }

    // ---- utils ----

    private static JSONObject readJson(String path) throws IOException {
        return new JSONObject(Files.readString(Paths.get(path), StandardCharsets.UTF_8));
    }

    private static void parseArgs(String[] a) {
        Map<String,String> m = new LinkedHashMap<>();
        for (int i = 0; i + 1 < a.length; i += 2) m.put(a[i], a[i+1]);

        bearerToken      = nvl(m.get("--token"), System.getenv("ZEPHYR_TOKEN"));
        projectKey       = nvl(m.get("--projectKey"), System.getenv("PROJECT_KEY"));
        resultsPath      = nvl(m.get("--results"), resultsPath);
        mappingPath      = nvl(m.get("--mapping"), mappingPath);
        cyclePrefix      = nvl(m.get("--cyclePrefix"), cyclePrefix);

        // new options
        cycleNameOverride = m.get("--cycleName"); // exact name override
        timestampFormat   = nvl(m.get("--timestampFormat"), timestampFormat);
        includeTimestamp  = !m.containsKey("--noStamp");
    }

    private static void require(String n, String v){ if (v == null || v.isBlank()) throw new IllegalArgumentException("Missing: " + n); }
    private static String nvl(String v, String d){ return (v==null||v.isBlank())?d:v; }
    private static void log(String s){ System.out.println("[ZephyrPublisher] " + s); }
}

/*
package com.agi.zephyr;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class ZephyrPublisher {

    // Zephyr Scale Cloud v2 base
    private static final String BASE = "https://api.zephyrscale.smartbear.com/v2";

    // Args/env
    private static String bearerToken;                                // --token or env ZEPHYR_TOKEN (Zephyr Scale API token you tested with curl)
    private static String projectKey;                                 // --projectKey or env PROJECT_KEY (e.g., "AEA")
    private static String resultsPath = "test-output/testng-results.xml";   // --results
    private static String mappingPath = "zephyr-integration/mapping.json";  // --mapping
    private static String cyclePrefix = "Daily Regression - ";              // --cyclePrefix

    public static void main(String[] args) {
        try {
            parseArgs(args);
            require("token", bearerToken);
            require("projectKey", projectKey);

            // Build cycle name like "Daily Regression - 12 Aug"
            String cycleName = cyclePrefix + new SimpleDateFormat("dd MMM").format(new Date());
            log("Cycle: " + cycleName);

            // 1) Create cycle -> get KEY (and log id+key)
            String cycleKey = createCycle(cycleName, projectKey);
            if (cycleKey == null || cycleKey.isBlank()) {
                throw new RuntimeException("Failed to create cycle (no key returned)");
            }
            log("cycleKey=" + cycleKey);

            // 2) Load mapping + parse TestNG
            JSONObject mapping = readJson(mappingPath);
            JSONArray results = new TestNGParser().parse(resultsPath);

            // 3) Create executions with statusName
            for (int i = 0; i < results.length(); i++) {
                JSONObject r = results.getJSONObject(i);
                String lookup = r.optString("className");
                String status  = r.optString("status");
                if (lookup.isBlank() || status.isBlank()) continue;

                String testCaseKey = mapping.optString(lookup);
                if (testCaseKey == null || testCaseKey.isBlank()) {
                    log("[SKIP] No mapping for " + lookup);
                    continue;
                }

                String statusName = "PASS".equalsIgnoreCase(status) ? "Pass" : "Fail"; // Scale expects names
                String execId = createExecution(projectKey, testCaseKey, cycleKey, statusName);
                log("Created execution " + execId + " for " + testCaseKey + " -> " + statusName);
            }

            log("Done.");
        } catch (Exception e) {
            System.err.println("[ZephyrPublisher][ERROR] " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    // ---- API calls (Zephyr Scale v2) ----

    */
/** Create a test cycle and return the cycle KEY (also logs ID). *//*

    private static String createCycle(String name, String projectKey) throws IOException {
        JSONObject body = new JSONObject()
                .put("name", name)
                .put("projectKey", projectKey);

        String resp = post(BASE + "/testcycles", body.toString());
        JSONObject obj = new JSONObject(resp);

        // Log both for visibility
        String id  = obj.optString("id", null);
        String key = obj.optString("key", null);
        log("cycleId=" + id + ", cycleKey=" + key);
        return key; // Use KEY in subsequent calls
    }

    */
/** Create a test execution inside the given cycleKey, with statusName ("Pass"/"Fail"). Returns execution ID. *//*

    private static String createExecution(String projectKey, String testCaseKey, String cycleKey, String statusName) throws IOException {
        JSONObject body = new JSONObject()
                .put("projectKey", projectKey)
                .put("testCaseKey", testCaseKey)
                .put("testCycleKey", cycleKey)   // IMPORTANT: key, not id
                .put("statusName", statusName);  // Provide status at creation

        String resp = post(BASE + "/testexecutions", body.toString());
        return new JSONObject(resp).optString("id", null);
    }

    // ---- HTTP helpers ----

    private static String post(String url, String body) throws IOException {
        HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
        c.setRequestMethod("POST");
        c.setDoOutput(true);
        setHeaders(c);
        try (OutputStream os = c.getOutputStream()) {
            os.write(body.getBytes(StandardCharsets.UTF_8));
        }
        return drain(c);
    }

    private static void setHeaders(HttpURLConnection c) {
        c.setRequestProperty("Authorization", "Bearer " + bearerToken);
        c.setRequestProperty("Content-Type", "application/json");
    }

    private static String drain(HttpURLConnection c) throws IOException {
        int code = c.getResponseCode();
        InputStream is = (code >= 200 && code < 300) ? c.getInputStream() : c.getErrorStream();
        String text = (is == null) ? "" : new String(is.readAllBytes(), StandardCharsets.UTF_8);
        if (code < 200 || code >= 300) {
            throw new IOException(c.getRequestMethod() + " " + c.getURL() + " -> " + code + " :: " + text);
        }
        return text;
    }

    // ---- utils ----

    private static JSONObject readJson(String path) throws IOException {
        return new JSONObject(Files.readString(Paths.get(path), StandardCharsets.UTF_8));
    }

    private static void parseArgs(String[] a) {
        Map<String,String> m = new LinkedHashMap<>();
        for (int i = 0; i + 1 < a.length; i += 2) m.put(a[i], a[i+1]);
        bearerToken = nvl(m.get("--token"), System.getenv("ZEPHYR_TOKEN"));
        projectKey  = nvl(m.get("--projectKey"), System.getenv("PROJECT_KEY"));
        resultsPath = nvl(m.get("--results"), resultsPath);
        mappingPath = nvl(m.get("--mapping"), mappingPath);
        cyclePrefix = nvl(m.get("--cyclePrefix"), cyclePrefix);
    }

    private static void require(String n, String v){ if (v == null || v.isBlank()) throw new IllegalArgumentException("Missing: " + n); }
    private static String nvl(String v, String d){ return (v==null||v.isBlank())?d:v; }
    private static void log(String s){ System.out.println("[ZephyrPublisher] " + s); }
}
*/
