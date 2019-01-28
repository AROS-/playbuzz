package com.playbuzz.automation.buzzapp.pages;

import com.playbuzz.automation.core.ui.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddMediaModal extends BasePage {

    private UIElement uploadBtn = UIElement.getUIElement(By.xpath("//*[@translate='MP.MODAL.MENU.FILE_LABEL']"),
            driver);
    private UIElement closeBtn = UIElement.getUIElement(By.cssSelector(".close"),
            driver);
    private UIElement dropFileArea = UIElement.getUIElement(By.cssSelector(".file-source"),
            driver);
    private UIElement progressCircle = UIElement
            .getUIElement(By.cssSelector(".media-provider-progress-screen"), driver);
    private UIElement uploadedImg = UIElement.getUIElement(By.cssSelector(".img-responsive"),
            driver);

    public AddMediaModal(WebDriver driver) {
        super(driver);
    }

    public CreateFlipCardPage uploadImage(String imagePath) {
        dropFileArea.dragAndDrop(imagePath);
        // TODO Replace with something smarter
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {

        }
        return new CreateFlipCardPage(driver);
    }

    public AddMediaModal clickUploadBtn() {
        uploadBtn.clickJS();
        return this;
    }

    public CreateFlipCardPage clickCloseBtn() {
        closeBtn.clickJS();
        return new CreateFlipCardPage(driver);
    }

}
