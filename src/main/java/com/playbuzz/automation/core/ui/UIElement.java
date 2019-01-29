package com.playbuzz.automation.core.ui;

import com.playbuzz.automation.core.enums.JSScript;
import com.playbuzz.automation.core.utils.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.ChronoUnit.SECONDS;

public class UIElement {

    private WebDriver webDriver;
    private WebElement webElement;
    private By byLocator;
    private Wait wait;
    private final long waitTimeout;
    private final long pollingTimeout = 100;
    private final long implicitlyWait = 1;
    private JavascriptExecutor javascriptExecutor;
    private static final Config config = Config.create();


    protected UIElement(By byLocator, WebDriver driver) {
        this.byLocator = byLocator;
        this.webDriver = driver;
        this.waitTimeout = config.getElementPresentTimeout();
        if (wait == null) {
            wait = new FluentWait(webDriver)
                    .withTimeout(Duration.of(waitTimeout, SECONDS))
                    .pollingEvery(Duration.of(pollingTimeout, MILLIS))
                    .ignoring(NoSuchElementException.class);
        }
        javascriptExecutor = (JavascriptExecutor) webDriver;
    }

    public static UIElement getUIElement(By byLocator, WebDriver driver) {
        return new UIElement(byLocator, driver);
    }

    public static List<UIElement> getUIElements(By byLocator, WebDriver driver) {
        List<WebElement> webElementList = driver.findElements(byLocator);
        List<UIElement> uiElementList = new ArrayList<>();
        for (WebElement webElement : webElementList) {
            UIElement uiElement = new UIElement(byLocator, driver);
            uiElement.setInternalWebElement(webElement);
            uiElementList.add(uiElement);
        }
        return uiElementList;
    }

    protected void initializeWebElementIfNull() {
        if (this.webElement == null || !isElementStale()) {
            webElement = webDriver.findElement(byLocator);
        }
    }

    public UIElement waitForElementToBeClickable() {
        return wait(ExpectedConditions.elementToBeClickable(byLocator));
    }

    public UIElement waitForElementToBeVisible() {
        return wait(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    public UIElement waitForElementToBePresent() {
        return wait(ExpectedConditions.presenceOfElementLocated(byLocator));
    }

    public <T> UIElement wait(ExpectedCondition<T> expectedConditions) {
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(expectedConditions);
        webDriver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        return this;
    }

    public WebElement getInternalWebElement() {
        return this.webElement;
    }

    public void setInternalWebElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public boolean isDisplayed() {
        try {
            initializeWebElementIfNull();
            return this.getInternalWebElement().isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            return false;
        }
    }

    public boolean isPresent() {
        return webDriver.findElements(byLocator).size() > 0;
    }

    public UIElement click() {
        initializeWebElementIfNull();
        webElement.click();
        return this;
    }

    public UIElement clickJS() {
        initializeWebElementIfNull();
        ((JavascriptExecutor) webDriver).executeScript(JSScript.CLICK.getScript(), webElement);
        return this;
    }

    public UIElement setText(String text) {
        initializeWebElementIfNull();
        webElement.sendKeys(text);
        return this;
    }

    public UIElement setValue(String text) {
        initializeWebElementIfNull();
        ((JavascriptExecutor) webDriver).executeScript(String.format("arguments[0].value='%s';", text),
                webElement);
        return this;
    }

    public UIElement clear() {
        initializeWebElementIfNull();
        webElement.clear();
        return this;
    }

    public UIElement select(String option) {
        initializeWebElementIfNull();
        new Select(this.getInternalWebElement()).selectByVisibleText(option);
        return this;
    }

    public UIElement setCheckbox(boolean checked) {
        initializeWebElementIfNull();
        if (webElement.isSelected() ^ checked) {
            clickJS();
        }
        return this;
    }

    public String getAttribute(String attribute) {
        initializeWebElementIfNull();
        return webElement.getAttribute(attribute);
    }

    public String getText() {
        initializeWebElementIfNull();
        return webElement.getText();
    }

    public String getCssValue(String cssValue) {
        initializeWebElementIfNull();
        return webElement.getCssValue(cssValue);
    }

    public String getInnerValue() {
        initializeWebElementIfNull();
        return webElement.getAttribute("value");
    }

    public String getInnerHTML() {
        initializeWebElementIfNull();
        return webElement.getAttribute("innerHTML");
    }

    public String getSelectedOption() {
        initializeWebElementIfNull();
        return new Select(this.getInternalWebElement()).getFirstSelectedOption().getText();
    }

    public List<String> getOptions() {
        initializeWebElementIfNull();
        List<WebElement> list = new Select(this.getInternalWebElement()).getOptions();
        return list.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isEnabled() {
        initializeWebElementIfNull();
        return webElement.isEnabled();
    }

    public boolean isSelected() {
        initializeWebElementIfNull();
        return webElement.isSelected();
    }

    public String getTagName() {
        initializeWebElementIfNull();
        return webElement.getTagName();
    }

    public Dimension getSize() {
        initializeWebElementIfNull();
        return webElement.getSize();
    }

    public UIElement refresh() {
        webElement = null;
        this.webElement = webDriver.findElement(byLocator);
        return this;
    }

    public UIElement scroll() {
        initializeWebElementIfNull();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", this.webElement);
        return this;
    }

    public UIElement scroll(int x, int y) {
        initializeWebElementIfNull();
        javascriptExecutor.executeScript(String.format("window.scrollBy(%s, %s);", x, y));
        return this;
    }

    public UIElement hover() {
        initializeWebElementIfNull();
        new Actions(webDriver).moveToElement(webElement).build().perform();
        return this;
    }

    public UIElement focus() {
        javascriptExecutor.executeScript("arguments[0].focus()", webElement);
        return this;
    }

    public UIElement unfocus() {
        javascriptExecutor.executeScript("arguments[0].blur()", webElement);
        return this;
    }

    public List<UIElement> getChildElements(By byLocator) {
        initializeWebElementIfNull();
        List<WebElement> webElementList = webElement.findElements(byLocator);
        List<UIElement> uiElementList = new ArrayList<>();
        for (WebElement webElement : webElementList) {
            UIElement uiElement = new UIElement(byLocator, this.webDriver);
            uiElement.setInternalWebElement(webElement);
            uiElementList.add(uiElement);
        }
        return uiElementList;
    }

    public UIElement getChildElement(By byLocator) {
        initializeWebElementIfNull();
        UIElement uiElement = new UIElement(byLocator, this.webDriver);
        uiElement.setInternalWebElement(webElement.findElement(byLocator));
        return uiElement;
    }

    public UIElement getParentElement() {
        initializeWebElementIfNull();
        UIElement uiElement = new UIElement(byLocator, this.webDriver);
        uiElement.setInternalWebElement(webElement.findElement(By.xpath("..")));
        return uiElement;
    }

    public void waitForElementInvisibility() {
        // TODO think of better logic or add all locators (tag, name, linktext)
        String[] byLocatorStrings = byLocator.toString().split("By\\.(id)?(cssSelector)?(xpath)?:");
        if (byLocatorStrings.length != 2) {
            throw new RuntimeException("Not supported locator. " +
                    "Only elements found by id, xpath or css selector can use this method");
        }
        String byLocatorString = byLocatorStrings[1];
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean)((JavascriptExecutor) driver)
                        .executeScript(
                                String.format("return $('%s').is(':visible') == false", byLocatorString));
            }
        });
    }

    public boolean isElementStale() {
        try {
            return webElement.isEnabled();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean contains(String attribute, String value) {
        return getAttribute(attribute).contains(value);
    }

    public boolean waitAndCheckAttribute(String attribute, String value) {
        try {
            contains(attribute, value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean contains(String attribute) {
        return getAttribute(attribute) != null;
    }

    public void waitForEndOfAllAjaxes() {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean)((JavascriptExecutor) driver)
                        .executeScript("return jQuery.active == 0");
            }
        });
    }

    public void waitForAttributeValue(String attributeName, String attributeValue) {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return contains(attributeName, attributeValue);
            }
        });
    }

    public UIElement dragAndDrop(String filePath) {
        initializeWebElementIfNull();
        File file = new File(filePath);

        if (!file.exists()) {
            throw new WebDriverException("File not found: " + filePath);
        }

        JavascriptExecutor jse = (JavascriptExecutor) webDriver;

        WebElement input =  (WebElement)jse.executeScript(JSScript.DRAG_AND_DROP.getScript(),
                this.getInternalWebElement(), 0, 0);
        input.sendKeys(file.getAbsoluteFile().toString());
        wait.until(ExpectedConditions.stalenessOf(input));
        return this;
    }

    public void waitForAngularLoad() {

        JavascriptExecutor jse = (JavascriptExecutor) webDriver;

        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

        ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
                .executeScript(angularReadyScript).toString());

        boolean angularReady = Boolean.valueOf(jse.executeScript(angularReadyScript).toString());

        if(!angularReady) {
            wait.until(angularLoad);
        }
    }

}