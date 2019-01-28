package com.playbuzz.automation.buzzapp.pages;

import com.playbuzz.automation.core.ui.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewStoryPage extends BasePage {

    private UIElement createFlipCardBtn = UIElement
            .getUIElement(By.xpath("//a[contains(@href, 'createscreen_flipCard')]"),
            driver);

    public NewStoryPage(WebDriver driver) {
        super(driver);
    }

    public CreateFlipCardPage clickCreateFlipCardBtn() {
        createFlipCardBtn.waitForElementToBeVisible().clickJS();
        return new CreateFlipCardPage(driver);
    }

}
