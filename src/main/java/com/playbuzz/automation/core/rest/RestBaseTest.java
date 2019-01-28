package com.playbuzz.automation.core.rest;

import com.playbuzz.automation.core.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class RestBaseTest extends BaseTest {

    protected HttpClient httpClient;

    @BeforeTest(alwaysRun = true)
    public void initTest() {
        if (httpClient == null) {
            httpClient = HttpClient.create();
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearTest() {
        if (httpClient != null) {
            httpClient.close();
            httpClient = null;
        }
    }

}
