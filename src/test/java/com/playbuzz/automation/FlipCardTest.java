package com.playbuzz.automation;

import com.playbuzz.automation.buzzapp.models.FlipCard;
import com.playbuzz.automation.buzzapp.models.User;
import com.playbuzz.automation.buzzapp.pages.*;
import com.playbuzz.automation.core.ui.UIBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlipCardTest extends UIBaseTest {

    @Test(dataProvider = "GenericDataProvider")
    public void checkFlipCard(User user, FlipCard flipCard) {

        HomePage homePage = new HomePage(driver);

        LoginPage loginPage = homePage.clickLoginBtn();
        loginPage
                .emailInput(user.getEmail())
                .passwordInput(user.getPassword());

        NewStoryPage newStoryPage = loginPage.clickLoginBtn();

        CreateFlipCardPage createFlipCardPage = newStoryPage.clickCreateFlipCardBtn();
        AfterPublishPage afterPublishPage = createFlipCardPage
                .clickAddHeaderBtn()
                .setSectionTitle(flipCard.getHeaderText())
                .clickAddTextBtn()
                .setFlipCardText(flipCard.getCardText())
                .clickBackCardBtn()
                .clickInnerDeleteBtn()
                .clickAddImageBtn()
                .clickUploadBtn()
                .uploadImage(flipCard.getImagePath())
                .clickPublishBtn();

        ItemPage itemPage = afterPublishPage.clickViewStoryBtn();
        itemPage.clickFlipCard();

        Assert.assertTrue(itemPage.isFlipped(), "The card was not flipped");

        Assert.assertTrue(itemPage.clickShareToFacebookBtn(), "Facebook window was not opened");

    }

}
