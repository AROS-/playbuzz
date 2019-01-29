package com.playbuzz.automation.buzzapp.pages;

import com.playbuzz.automation.core.ui.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class ItemPage extends BasePage {

    private static final String FACEBOOK_URL = "https://www.facebook.com/";

    private UIElement flipCardPanel = UIElement.getUIElement(By.cssSelector(".pb-flip-card-viewer"),
            driver);
    private UIElement shareFacebookBtn = UIElement.getUIElement(By.cssSelector(".pb-share-button.facebook"),
            driver);


    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public ItemPage clickFlipCard() {
        flipCardPanel.waitForElementToBeClickable().click();
        return this;
    }

    public boolean clickShareToFacebookBtn() {
        shareFacebookBtn.scroll(0, 1000);
        shareFacebookBtn.repeatClick();
        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(windows.size() - 1));
        return driver.getCurrentUrl().contains(FACEBOOK_URL);
    }

    public boolean isFlipped() {
        return flipCardPanel.waitAndCheckAttribute("class", "flipped");
    }
}
