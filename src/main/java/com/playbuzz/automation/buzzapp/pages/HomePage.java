package com.playbuzz.automation.buzzapp.pages;

import com.playbuzz.automation.core.ui.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private UIElement createBtn = UIElement.getUIElement(By.cssSelector(".pb-navbar-btn-create"), driver);
    private UIElement loginBtn = UIElement.getUIElement(By.cssSelector(".pb-navbar-btn-sign-in"), driver);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickCreateBtn() {
        createBtn.clickJS();
        return this;
    }

    public LoginPage clickLoginBtn() {
        loginBtn.click();
        return new LoginPage(driver);
    }

}
