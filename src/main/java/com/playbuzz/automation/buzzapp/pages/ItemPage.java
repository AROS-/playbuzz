package com.playbuzz.automation.buzzapp.pages;

import com.playbuzz.automation.core.ui.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class ItemPage extends BasePage {

    private UIElement flipCardPanel = UIElement.getUIElement(By.cssSelector(".pb-flip-card-viewer"),
            driver);
    private UIElement shareFacebookBtn = UIElement.getUIElement(By.cssSelector(".pb-share-button.facebook"),
            driver);


    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public ItemPage clickFlipCard() {
        // TODO Replace with something smarter
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        flipCardPanel.waitForElementToBeClickable().click();
        return this;
    }

    public boolean clickShareToFacebookBtn() {
        shareFacebookBtn.scroll(0, 1000);
        // TODO Replace with something smarter
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        shareFacebookBtn.waitForElementToBeVisible().click();
        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(windows.size() - 1));
        return driver.getCurrentUrl().contains("facebook");
    }

    public boolean isFlipped() {
        return flipCardPanel.waitAndCheckAttribute("class", "flipped");
    }
}
