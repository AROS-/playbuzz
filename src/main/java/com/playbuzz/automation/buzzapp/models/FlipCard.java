package com.playbuzz.automation.buzzapp.models;

import com.playbuzz.automation.core.BaseTestModel;
import com.playbuzz.automation.core.annotations.DataProviderConstructor;
import com.playbuzz.automation.core.annotations.TestData;

public class FlipCard extends BaseTestModel {

    private String cardText;
    private String headerText;
    private String imagePath;

    private FlipCard[] testData;

    @DataProviderConstructor
    public FlipCard(String testName, String method, String param) {
        super(testName, method, param);
        FlipCard flipCard = deserialize(FlipCard.class);
        this.cardText = flipCard.getCardText();
        this.headerText = flipCard.getHeaderText();
        this.imagePath = flipCard.getImagePath();
        this.testData = flipCard.getTestData();
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @TestData
    public FlipCard[] getTestData() {
        return testData;
    }

    public void setTestData(FlipCard[] testData) {
        this.testData = testData;
    }
}
