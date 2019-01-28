package com.playbuzz.automation.core.ui;

import com.playbuzz.automation.core.BaseTest;
import com.playbuzz.automation.core.utils.Config;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class UIBaseTest extends BaseTest {

    protected WebDriver driver;
    private Config config = Config.create();

    @BeforeSuite
    public void initSuite() {
        driver = WebDriverFactory.getDriver();
        driver.get(config.getBaseUrl());
    }

    @AfterSuite(alwaysRun = true)
    public void tearSuite() {
        if (driver != null) {
            driver.quit();
        }
    }

}
