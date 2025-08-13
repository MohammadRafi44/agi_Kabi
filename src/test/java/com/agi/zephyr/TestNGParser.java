package com.agi.zephyr;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class TestNGParser {
    public JSONArray parse(String filePath) throws Exception {
        JSONArray out = new JSONArray();

        File file = new File(filePath);
        if (!file.exists()) throw new RuntimeException("TestNG file not found: " + file.getAbsolutePath());

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        doc.getDocumentElement().normalize();

        NodeList classNodes = doc.getElementsByTagName("class");
        for (int i = 0; i < classNodes.getLength(); i++) {
            Element c = (Element) classNodes.item(i);
            String fqcn = c.getAttribute("name");
            if (fqcn == null || fqcn.isBlank()) continue;
            String simple = fqcn.contains(".") ? fqcn.substring(fqcn.lastIndexOf('.') + 1) : fqcn;

            NodeList methods = c.getElementsByTagName("test-method");
            for (int j = 0; j < methods.getLength(); j++) {
                Element m = (Element) methods.item(j);
                if ("true".equalsIgnoreCase(m.getAttribute("is-config"))) continue;

                String status = m.getAttribute("status"); // PASS / FAIL / SKIP
                if (status == null || status.isBlank() || "SKIP".equalsIgnoreCase(status)) continue;

                String name = m.getAttribute("name");
                if (name == null || name.isBlank()) continue;

                JSONObject o = new JSONObject();
                o.put("className", simple + "#" + name); // mapping key
                o.put("status", status);
                out.put(o);
            }
        }
        return out;
    }
}
