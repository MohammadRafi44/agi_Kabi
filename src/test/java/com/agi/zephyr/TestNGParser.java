package com.agi.zephyr;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/** Emits [{"className":"Sanity_Suite#loginValidDriverID","status":"PASS"}, ...] */
public class TestNGParser {

    public JSONArray parse(String filePath) throws Exception {
        JSONArray out = new JSONArray();

        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("TestNG results file not found: " + file.getAbsolutePath());
        }

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        doc.getDocumentElement().normalize();

        NodeList classNodes = doc.getElementsByTagName("class");
        for (int i = 0; i < classNodes.getLength(); i++) {
            Element classEl = (Element) classNodes.item(i);
            String fqcn = classEl.getAttribute("name");
            if (fqcn == null || fqcn.isBlank()) continue;

            String simpleClass = simpleName(fqcn); // com.x.y.Sanity_Suite -> Sanity_Suite

            NodeList methodNodes = classEl.getElementsByTagName("test-method");
            for (int j = 0; j < methodNodes.getLength(); j++) {
                Element m = (Element) methodNodes.item(j);

                // skip @Before/@After and SKIPs
                if ("true".equalsIgnoreCase(m.getAttribute("is-config"))) continue;
                String status = m.getAttribute("status"); // PASS | FAIL | SKIP
                if (status == null || status.isBlank() || "SKIP".equalsIgnoreCase(status)) continue;

                String methodName = m.getAttribute("name");
                if (methodName == null || methodName.isBlank()) continue;

                JSONObject obj = new JSONObject();
                obj.put("className", simpleClass + "#" + methodName);
                obj.put("status", status);
                out.put(obj);
            }
        }
        return out;
    }

    private String simpleName(String fqcn) {
        int idx = fqcn.lastIndexOf('.');
        return (idx >= 0 && idx < fqcn.length() - 1) ? fqcn.substring(idx + 1) : fqcn;
    }
}
