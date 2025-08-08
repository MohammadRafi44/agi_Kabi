//package com.agi.zephyr;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.apache.http.client.methods.*;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.*;
//import org.apache.http.util.EntityUtils;
//
//import java.io.File;
//import java.nio.charset.StandardCharsets;
//import java.time.Instant;
//import java.util.*;
//
//public class ZephyrPublisher {
//
//    private static String accessKey;
//    private static String secretKey;
//    private static String accountId;
//    private static String projectId;
//    private static String cyclePrefix;
//    private static File resultsFile;
//    private static File mappingFile;
//
//    private static final String BASE_URL = "https://prod-api.zephyr4jiracloud.com/connect/public/rest/api/1.0";
//
//    public static void main(String[] args) throws Exception {
//        Map<String, String> params = parseArgs(args);
//
//        resultsFile = new File(params.get("--results"));
//        mappingFile = new File(params.get("--mapping"));
//        accessKey = params.get("--zapiAccessKey");
//        secretKey = params.get("--secretKey");
//        accountId = params.get("--accountId");
//        projectId = params.get("--projectId");
//        cyclePrefix = params.get("--cyclePrefix");
//
//        if (!resultsFile.exists()) throw new RuntimeException("Results file not found: " + resultsFile);
//        if (!mappingFile.exists()) throw new RuntimeException("Mapping file not found: " + mappingFile);
//
//        // 1. Parse TestNG results
//        Map<String, String> testCaseMapping = new TestNGParser(mappingFile).getTestCaseMapping(resultsFile);
//
//        // 2. Create test cycle
//        String cycleId = createTestCycle(cyclePrefix);
//
//        // 3. Add executions
//        for (Map.Entry<String, String> e : testCaseMapping.entrySet()) {
//            createExecutionAndUpdate(e.getKey(), e.getValue(), cycleId);
//        }
//
//        System.out.println("[ZephyrPublisher] Publishing complete.");
//    }
//
//    private static String createTestCycle(String prefix) throws Exception {
//        String url = BASE_URL + "/cycle";
//        String payload = String.format("{\"name\":\"%s%s\",\"projectId\":%s}", prefix, Instant.now().toString(), projectId);
//        String response = post(url, payload);
//        JsonNode node = new ObjectMapper().readTree(response);
//        return node.get("id").asText();
//    }
//
//    private static void createExecutionAndUpdate(String methodName, String issueKey, String cycleId) throws Exception {
//        String createExecUrl = BASE_URL + "/execution";
//        String payload = String.format("{\"cycleId\":\"%s\",\"issueId\":\"%s\",\"projectId\":%s}", cycleId, issueKey, projectId);
//        String execResponse = post(createExecUrl, payload);
//
//        JsonNode node = new ObjectMapper().readTree(execResponse);
//        String execId = node.get("id").asText();
//
//        // Update execution status (1 = Pass, 2 = Fail)
//        String status = "1";
//        String updateUrl = BASE_URL + "/execution/" + execId + "/execute";
//        String updatePayload = String.format("{\"status\":{\"id\":%s}}", status);
//        put(updateUrl, updatePayload);
//    }
//
//    private static String post(String url, String payload) throws Exception {
//        HttpPost post = new HttpPost(url);
//        post.setEntity(new StringEntity(payload, StandardCharsets.UTF_8));
//        post.setHeader("Content-Type", "application/json");
//        signRequest(post, "POST", url);
//        try (CloseableHttpClient client = HttpClients.createDefault();
//             CloseableHttpResponse resp = client.execute(post)) {
//            return EntityUtils.toString(resp.getEntity());
//        }
//    }
//
//    private static String put(String url, String payload) throws Exception {
//        HttpPut put = new HttpPut(url);
//        put.setEntity(new StringEntity(payload, StandardCharsets.UTF_8));
//        put.setHeader("Content-Type", "application/json");
//        signRequest(put, "PUT", url);
//        try (CloseableHttpClient client = HttpClients.createDefault();
//             CloseableHttpResponse resp = client.execute(put)) {
//            return EntityUtils.toString(resp.getEntity());
//        }
//    }
//
//    private static void signRequest(HttpRequestBase request, String method, String uri) {
//        String jwt = generateJWT(method, uri);
//        request.setHeader("Authorization", "JWT " + jwt);
//        request.setHeader("zapiAccessKey", accessKey);
//    }
//
//    private static String generateJWT(String method, String uri) {
//        long nowMillis = System.currentTimeMillis();
//        long expMillis = nowMillis + 360000; // 6 minutes
//        return Jwts.builder()
//                .setIssuedAt(new Date(nowMillis))
//                .setExpiration(new Date(expMillis))
//                .setIssuer(accountId)
//                .claim("qsh", QshGenerator.generateQsh(method, uri))
//                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
//                .compact();
//    }
//
//    private static Map<String, String> parseArgs(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        String key = null;
//        for (String arg : args) {
//            if (arg.startsWith("--")) {
//                key = arg;
//            } else if (key != null) {
//                map.put(key, arg);
//                key = null;
//            }
//        }
//        return map;
//    }
//}
