package com.playbuzz.automation.buzzapp.pages;

import com.playbuzz.automation.core.ui.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private UIElement emailInput = UIElement.getUIElement(By.id("elogin_email_field"),
            driver);
    private UIElement passwordInput = UIElement.getUIElement(By.id("elogin_pass_field"),
            driver);
    private UIElement loginBtn = UIElement.getUIElement(By.id("submitLogin"),
            driver);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage emailInput(String email) {
        emailInput.setText(email);
        return this;
    }

    public LoginPage passwordInput(String password) {
        passwordInput.setText(password);
        return this;
    }

    public NewStoryPage clickLoginBtn() {
        loginBtn.clickJS();
        return new NewStoryPage(driver);
    }

}
