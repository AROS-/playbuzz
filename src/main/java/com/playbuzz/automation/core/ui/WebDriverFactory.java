package com.playbuzz.automation.core.ui;

import com.playbuzz.automation.core.enums.Browser;
import com.playbuzz.automation.core.utils.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    private static Config config = Config.create();

    private WebDriverFactory() {}

    public static WebDriver getDriver() {

        return getDriver(Browser.getBrowser(config.getBrowser()),
                BrowserCapabilities
                        .getCapabilities(Browser.getBrowser(config.getBrowser())));

    }

    private static WebDriver getDriver(Browser browser, Capabilities capabilities) {

        String webdriversPath = config.getWebdriversPath();

        System.setProperty("wdm.targetPath", webdriversPath);

        switch (browser) {
            case CHROME:
                return getChromeDriver(capabilities);
            case FIREFOX:
                return getFirefoxDriver(capabilities);
            default:
                return getChromeDriver(capabilities);
        }

    }

    private static WebDriver getChromeDriver(Capabilities capabilities) {
        System.setProperty("wdm.chromeDriverVersion", config.getChromeDriverVersion());
        WebDriverManager.chromedriver().arch32().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        return new ChromeDriver(chromeOptions.merge(capabilities));
    }

    private static WebDriver getFirefoxDriver(Capabilities capabilities) {
        System.setProperty("wdm.geckoDriverVersion", config.getGeckoDriverVersion());
        WebDriverManager.firefoxdriver().arch32().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return new FirefoxDriver(firefoxOptions.merge(capabilities));
    }

}
