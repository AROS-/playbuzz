package com.playbuzz.automation.core.enums;

import java.util.regex.Pattern;

public enum Browser {

    CHROME("chrome"),
    FIREFOX("firefox");

    private static final Pattern FFPattern = Pattern.compile("(?i)F(ire)?.?F(ox)?.*");
    private static final Pattern CHROMEPattern = Pattern.compile("(?i)G?(OOGLE)?.?C(HROME)?.*");

    private final String browser;

    Browser(String browser) {
        this.browser = browser;
    }

    public static Browser getBrowser(String browser) {

        if (CHROMEPattern.matcher(browser).matches()) {
            return CHROME;
        } else if (FFPattern.matcher(browser).matches()) {
            return FIREFOX;
        } else {
            throw new RuntimeException(browser + " is not supported browser");
        }
    }
}
