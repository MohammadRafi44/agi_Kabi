package com.agi.api.base;

import com.agi.api.AgiAPI;
import com.alghurair.wrapper.base.AlGhurairAPIBaseTest;

public class AgiAPITest extends AlGhurairAPIBaseTest {

    public AgiAPI api;

    public AgiAPITest() {
        api = new AgiAPI();
    }

    // ---- step helpers (reusable) ----
    protected void step(String title) {
        logger.info(title);                    // uses the same ReportManager from APIBaseTest
    }
    protected void stepData(String title, Object data) {
        logger.info(title);
        try {
            String pretty = new com.google.gson.GsonBuilder()
                    .setPrettyPrinting().create().toJson(data);
            logger.addCodeBlock(pretty);
        } catch (Throwable t) {
            logger.addCodeBlock(String.valueOf(data));
        }
    }
}