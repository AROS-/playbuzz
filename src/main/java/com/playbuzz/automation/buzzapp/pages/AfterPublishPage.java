package com.playbuzz.automation.buzzapp.pages;

import com.playbuzz.automation.core.ui.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class AfterPublishPage extends BasePage {

    private UIElement viewStoryBtn = UIElement.getUIElement(By.xpath("//*[@translate='STORY_EMBED.VIEW_ITEM']"),
            driver);

    public AfterPublishPage(WebDriver driver) {
        super(driver);
    }

    public ItemPage clickViewStoryBtn() {
        viewStoryBtn.waitForElementToBeVisible().click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new ItemPage(driver);
    }
}
