package com.planit.tests.pages;



import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebElement;


import com.planit.tests.base.BaseTest;
import com.planit.tests.utilities.Utils;

public class ContactPage extends BaseTest {
    private static final Logger logger = Utils.getLogger(ContactPage.class);

 

    public void navigateToContact() {
   
        Utils.waitForClickable(getBy("headerContact"), 10).click();
        logger.info("User navigated to contact form");
    }

    public void clickSubmit() {
        Utils.waitForClickable(getBy("contactSubmit"), 10).click();
        logger.info("User clicked on submit with blank input");
    }

    public void clickBackBtn() {
       
        Utils.waitForClickable(getBy("backBtn"), 10).click();
        logger.info("User clicked back button on success screen");
    }

    public void fillMandatoryFields() {
        String name = BaseTest.getData("name");
        String email = BaseTest.getData("email");
        String message = BaseTest.getData("message");

        WebElement nameField = Utils.waitForElementVisible(getBy("enterName"), 10);
        nameField.clear();
        nameField.sendKeys(name);

        WebElement emailField = Utils.waitForElementVisible(getBy("enterEmail"), 10);
        emailField.clear();
        emailField.sendKeys(email);

        WebElement msgField = Utils.waitForElementVisible(getBy("enterMsg"), 10);
        msgField.clear();
        msgField.sendKeys(message);
    }

    public boolean isSuccessMessageDisplayed() {
        WebElement success = Utils.waitForElementVisible(getBy("successAlert"), 10);
        logger.info("Checking for success message visibility");
        return success.isDisplayed();
    }

    public boolean isErrorDisplayed(String key) {
        logger.info("Validating if error message is displayed");
        return Utils.waitForElementVisible(getBy(key), 10).isDisplayed();
    }

    public boolean isErrorGone(String key) {
        logger.info("Validating if error message has disappeared");
        return Utils.waitForinvisibility(getBy(key), 10);
    }

    public void waitForFeedbackPopupToDisappear() {
        try {
            logger.info("Waiting for feedback modal to disappear");
            Utils.waitForinvisibility(getBy("feedbackModalHeader"), 25);
        } catch (Exception e) {
            logger.error("Feedback modal did not disappear", e);
        }
    }

    public String getSuccessMessageText() {
        logger.info("Getting success message text");
        return Utils.waitForElementVisible(getBy("successResult"), 10).getText().trim();
    }
}

