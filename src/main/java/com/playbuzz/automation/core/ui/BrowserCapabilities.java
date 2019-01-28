package com.playbuzz.automation.core.ui;

import com.playbuzz.automation.core.enums.Browser;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserCapabilities {

    private BrowserCapabilities() {}

    public static Capabilities getCapabilities(Browser browser) {

        DesiredCapabilities capabilities;

        switch (browser) {

            case CHROME:
                capabilities = DesiredCapabilities.chrome();
                capabilities.setAcceptInsecureCerts(true);
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                        UnexpectedAlertBehaviour.IGNORE);
                return capabilities;
            case FIREFOX:
                capabilities = DesiredCapabilities.firefox();
                capabilities.setAcceptInsecureCerts(true);
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                        UnexpectedAlertBehaviour.IGNORE);
                return capabilities;
            default:
                capabilities = DesiredCapabilities.chrome();
                capabilities.setAcceptInsecureCerts(true);
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                        UnexpectedAlertBehaviour.IGNORE);
                return capabilities;
        }
    }

}
