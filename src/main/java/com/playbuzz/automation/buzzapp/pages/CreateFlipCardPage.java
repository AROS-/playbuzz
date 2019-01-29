package com.playbuzz.automation.buzzapp.pages;

import com.playbuzz.automation.core.ui.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CreateFlipCardPage extends BasePage {

    private UIElement previewBtn = UIElement.getUIElement(By.cssSelector(".preview-button"),
            driver);
    private UIElement publishBtn = UIElement.getUIElement(By.cssSelector(".pb-btn-green"),
            driver);
    private UIElement addHeaderBtn = UIElement.getUIElement(By.cssSelector(".story-add-header"),
                    driver);
    private UIElement addTextBtn = UIElement.getUIElement(By.cssSelector(".text-card"),
                    driver);
    private UIElement addImageBtn = UIElement.getUIElement(By.cssSelector(".source-button.file"),
            driver);
    private UIElement frontCardBtn = UIElement.getUIElement(By.xpath("//div[@media = 'vm.media.frontMedia']"),
            driver);
    private UIElement backCardBtn = UIElement.getUIElement(By.xpath("//div[@media = 'vm.media.backMedia']"),
            driver);
    private UIElement backCardTitle = UIElement.getUIElement(By.xpath("//*[@translate='MP.FLIP_CARD.BACK_TITLE']"),
            driver);
    private UIElement textEditorInput = UIElement.getUIElement(By.id("text-editor"),
            driver);
    private UIElement storyTitleInput = UIElement.getUIElement(By.cssSelector(".story-title"),
            driver);
    private UIElement sectionTitleInput = UIElement.getUIElement(By.cssSelector(".ql-editor"),
            driver);
    private UIElement innerDeleteBtn = UIElement.getUIElement(By.cssSelector(".inner-delete-button"),
            driver);


    public CreateFlipCardPage(WebDriver driver) {
        super(driver);
    }

    public CreateFlipCardPage clickAddHeaderBtn() {
        addHeaderBtn.clickJS();
        return this;
    }

    public CreateFlipCardPage setStoryTitle(String text) {
        storyTitleInput.clear().setText(text);
        return this;
    }

    public CreateFlipCardPage setSectionTitle(String text) {
        sectionTitleInput.setText(text);
        return this;
    }

    public CreateFlipCardPage clickAddTextBtn() {
        addTextBtn.clickJS().clickJS();
        return this;
    }

    public AddMediaModal clickAddImageBtn() {
        List<UIElement> elements = UIElement.getUIElements(By.cssSelector(".source-button.file"), driver);
        elements.get(1).repeatClick();
        return new AddMediaModal(driver);
    }

    public CreateFlipCardPage clickFrontCardBtn() {
        frontCardBtn.clickJS();
        return this;
    }

    public CreateFlipCardPage clickBackCardBtn() {
        backCardBtn.clickJS();
        backCardTitle.waitForElementToBeVisible();
        return this;
    }

    public CreateFlipCardPage setFlipCardText(String text) {
        UIElement.getUIElement(By.id("text-editor"),
                driver).clickJS();
        UIElement.getUIElements(By.id("text-editor"),
                driver).get(1).setText(text);
        return this;
    }

    public CreateFlipCardPage clickPreviewBtn() {
        previewBtn.clickJS();
        return this;
    }

    public AfterPublishPage clickPublishBtn() {
        publishBtn.clickJS();
        return new AfterPublishPage(driver);
    }

    public CreateFlipCardPage clickInnerDeleteBtn() {
        innerDeleteBtn.clickJS();
        return this;
    }


}
