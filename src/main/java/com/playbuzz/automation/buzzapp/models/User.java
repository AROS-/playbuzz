package com.playbuzz.automation.buzzapp.models;

import com.playbuzz.automation.core.BaseTestModel;
import com.playbuzz.automation.core.annotations.DataProviderConstructor;
import com.playbuzz.automation.core.annotations.TestData;

public class User extends BaseTestModel {

    private String email;
    private String password;

    private User[] testData;

    @DataProviderConstructor
    public User(String testName, String method, String param) {
        super(testName, method, param);
        User user = deserialize(User.class);
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.testData = user.getTestData();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @TestData
    public User[] getTestData() {
        return testData;
    }

    public void setTestData(User[] testData) {
        this.testData = testData;
    }
}
