package com.agi.api.repo;

import com.alghurair.api.RestAssuredAPI;
import com.alghurair.manager.ReportManager;
import com.alghurair.manager.TestConfigManager;
import com.alghurair.manager.TestDataManager;
import io.restassured.http.ContentType;

import java.util.*;

public class RetrieveEmployeeDetails_API extends RestAssuredAPI {
    // --- add this ---
    private static final ReportManager logger = new ReportManager(RetrieveEmployeeDetails_API.class);
    // ----- constructor (keeps defaults in code, actual base URL can still come from Excel) -----
    public RetrieveEmployeeDetails_API() {
        super(ApiConfig.builder()
                .baseUri(TestConfigManager.getTestSettingsAPIBaseURL()) // will be ignored if APIDetails has absolute URL
                .defaultContentType(ContentType.JSON)
                .relaxedHttpsValidation(true)
                .logOnFailureOnly(true)
                .maxRetries(1)
                .retryDelayMs(300)
                .build());
    }
    // ====== 1) GETTER: pull everything from RunManager
    public Map<String, String> getRetrieveEmployeeDetails_ApiDetails(String reference) throws Exception {
        Map<String, String> m = getAPIDetails(reference);

        // --- Unirest-style request logging for Step 01 ---
        logger.info("Method  : " + m.getOrDefault("HTTPMethod", ""));
        logger.info("Endpoint: " + m.getOrDefault("EndPoint", ""));

        String base = m.getOrDefault("RequestURL", "");
        if (!base.isBlank()) {
            logger.info("BaseURL : " + base);
            logger.info("URL     : " + base + m.getOrDefault("EndPoint", ""));
        }

        logger.addCodeBlock("HeadersJSON:\n" + m.getOrDefault("HeadersJSON","{}"));

        String payload = m.getOrDefault("RequestPayload", "");
        if (!payload.isBlank()) {
            logger.addCodeBlock("RequestPayload:\n" + payload);
        }

        return m;
    }
    // ====== 1) GETTER: pull everything from RunManager (APIDetails + HeaderDetails + RequestPayloadDetails + ResponseDetails)
/*    public Map<String, String> getCafmWorkOrderAPIDetails(String reference) throws Exception {
        return getAPIDetails(reference);
    }*/
    /** Mirrors your old UnirestAPI#getAPIDetails(reference) */
    public Map<String, String> getAPIDetails(String reference) throws Exception {
        Map<String,String> api = TestDataManager.getTestDataAsMap("APIDetails", reference); // HTTPMethod, HeaderReference, RequestURL, EndPoint, RequestPayloadReference, ResponseReference

        String headersJson = "{}";
        if (api.containsKey("HeaderReference")) {
            Map<String,String> hdr = TestDataManager.getTestDataAsMap("HeaderDetails", api.get("HeaderReference"));
            headersJson = hdr.getOrDefault("RequestHeaders", "{}");
        }

        String payload = "";
        if (api.containsKey("RequestPayloadReference")) {
            Map<String,String> req = TestDataManager.getTestDataAsMap("RequestPayloadDetails", api.get("RequestPayloadReference"));
            payload = req.getOrDefault("RequestPayload", "");
        }

        Map<String,String> resp = new HashMap<>();
        if (api.containsKey("ResponseReference")) {
            resp = TestDataManager.getTestDataAsMap("ResponseDetails", api.get("ResponseReference")); // ExpectedResponseStatusCode, ExpectedResponsePayload, (Schema if you use)
        }

        Map<String,String> out = new LinkedHashMap<>();
        out.putAll(api);
        out.putAll(resp);
        out.put("HeadersJSON", headersJson);
        out.put("RequestPayload", payload);
        return out;
    }
    // ====== 2) ACTION: perform the API using the map (placeholders replaced from 'data')
    public ApiResponse retrieveEmployeeDetails(Map<String,String> data) {
// Replace {employeeNumber} etc from RunManager data
        String endpoint = applyPlaceholders(data.getOrDefault("EndPoint",""), data);
        data.put("EndPoint", endpoint); // optional: so logs show the final endpoint

        String url = resolveUrl(data.get("RequestURL"), endpoint);

/*        //String url = data.getOrDefault("RequestURL","") + data.getOrDefault("EndPoint","");
        String url = resolveUrl(
                data.get("RequestURL"),
                data.get("EndPoint")
        );*/

        String method = data.getOrDefault("HTTPMethod","POST").toUpperCase(Locale.ROOT);

        // headers from cell JSON (keep this)
        String hdrJson = applyPlaceholders(data.getOrDefault("HeadersJSON","{}"), data);
        Map<String,Object> headers = new LinkedHashMap<>(parseJsonMap(hdrJson));

        // force the runtime key from UserDetails to win (minimal, local change)
        String ocp = data.get("Ocp-Apim-Subscription-Key");
        if (ocp != null && !ocp.isBlank()) {
            headers.keySet().removeIf(k ->
                    "Ocp-Apim-Subscription-Key".equalsIgnoreCase(String.valueOf(k).trim())
            );
            headers.put("Ocp-Apim-Subscription-Key", ocp.trim());
        }



        // payload with placeholders replaced using all keys from 'data' (your earlier manual replaces, now generic)
        String payload = applyPlaceholders(data.getOrDefault("RequestPayload",""), data);
        data.put("RequestPayload", payload); // keep behavior

        var req = given();
        headers.forEach((k,v) -> req.header(String.valueOf(k), Objects.toString(v, "")));
        if (!payload.isBlank()) req.contentType(ContentType.JSON).body(payload);

        ApiResponse res = switch (method) {
            case "PUT"    -> req.put(url);
            case "PATCH"  -> req.patch(url);
            case "DELETE" -> req.delete(url);
            default       -> req.post(url);
        };

        data.put("ActualResponsePayload", res.asString()); // keep behavior

        // Extract field you used: Data.WorkOrders[0].ResponseMessage
        String responseMessage = res.jsonString("Message");
        if (responseMessage != null) data.put("response_Message", responseMessage);

        return res;
    }
    public void assertRetrieveEmployeeDetails(Map<String,String> data, ApiResponse res) {
        // 1) status code
        String exp = data.getOrDefault("ExpectedResponseStatusCode", "").trim();
        if (!exp.isBlank()) res.assertStatus(Integer.parseInt(exp), logger);
        // 2) node present
        String nodeList = data.getOrDefault("ExpectedNodePresent", "").trim();
        if (!nodeList.isBlank()) {
            for (String path : splitPaths(nodeList)) {
                res.assertNodePresent(path, logger);
            }
        }
        // 3) node value equals
        String fieldsJson = data.getOrDefault("ExpectedFieldsJSON", "").trim();
        if (!fieldsJson.isBlank()) {
            fieldsJson = applyPlaceholders(fieldsJson, data); // keep placeholder support
            Map<String,Object> expected = parseJsonMap(fieldsJson);
            expected.forEach((path, val) -> res.assertNodeEquals(path, val,logger));
        }
        // 4) extraction for chaining (RunManager column: ExtractJSON)
        RestAssuredAPI.extractJson(res, data, data.get("ExtractJSON"), logger);
    }







    // ===== utils =====
    private static String resolveUrl(String base, String endpoint) {
        String e = Optional.ofNullable(endpoint).orElse("").trim();
        String b = Optional.ofNullable(base).orElse("").trim();

        // If endpoint itself is absolute, use it as-is.
        if (e.startsWith("http://") || e.startsWith("https://")) return e;

        // If base from RunManager is absolute, join base + endpoint.
        if (b.startsWith("http://") || b.startsWith("https://")) {
            if (b.endsWith("/") && e.startsWith("/")) e = e.substring(1);
            return b + e;
        }

        // Fallback: let RestAssured’s baseUri (from TestConfig) handle it.
        return e;
    }
    @SuppressWarnings("unchecked")
    private static Map<String,Object> parseJsonMap(String json) {
        if (json == null || json.isBlank()) return new LinkedHashMap<>();
        try { return new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, Map.class); }
        catch (Exception e) { throw new IllegalArgumentException("Bad JSON: " + json, e); }
    }
    private static List<String> splitPaths(String s) {
        s = s.trim();
        // allow JSON array OR comma/pipe separated list OR single value
        if (s.startsWith("[") && s.endsWith("]")) {
            try {
                List<?> arr = new com.fasterxml.jackson.databind.ObjectMapper().readValue(s, List.class);
                List<String> out = new ArrayList<>();
                for (Object o : arr) out.add(String.valueOf(o).trim());
                return out;
            } catch (Exception ignore) { /* fall through */ }
        }
        return Arrays.stream(s.split("[,|]"))
                .map(String::trim).filter(x -> !x.isEmpty()).toList();
    }
    /** Replace {Key} or {{Key}} by matching any key in 'data' map (covers all your earlier manual replacements) */
    private static String applyPlaceholders(String text, Map<String,?> data) {
        if (text == null || text.isBlank()) return text;
        String out = text;
        for (var e : data.entrySet()) {
            String k = e.getKey();
            String v = Objects.toString(e.getValue(), "");
            out = out.replace("{"+k+"}", v).replace("{{"+k+"}}", v);
        }
        // normalize true/false/number strings if you need; JSON remains valid as strings are quoted in your sheet
        return out;
    }
}