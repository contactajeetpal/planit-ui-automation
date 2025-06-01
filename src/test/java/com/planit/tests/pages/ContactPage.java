package com.planit.tests.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;


import com.planit.tests.base.BaseTest;
import com.planit.tests.utilities.Utils;

public class ContactPage extends BaseTest{
	private static final Logger logger = Utils.getLogger(ContactPage.class);
	
	public void navigateToContact() {
        Utils.waitForClickable(getBy("headerContact"), 10).click();
        logger.info("user navigated in contact form");
    }

    public void clickSubmit() {
        Utils.waitForClickable(getBy("contactSubmit"), 10).click();
        logger.info("user perform click on on blank input");
    }
    public void clickBackBtn() {
    	Utils.waitForClickable(getBy("backBtn"), 10).click();
    	 logger.info("user perform back operation on success screen");
    }
   
    public void fillMandatoryFields() {
    	String name = BaseTest.getData("name");
        String email = BaseTest.getData("email");
        String message = BaseTest.getData("message");
    	
        // Fill Name field
        WebElement nameField = Utils.waitForElementVisible(getBy("enterName"), 10);
        nameField.clear();
        nameField.sendKeys(name);

        // Fill Email field
        WebElement emailField = Utils.waitForElementVisible(getBy("enterEmail"), 10);
        emailField.clear();
        emailField.sendKeys(email);

        // Fill Message field
        WebElement msgField = Utils.waitForElementVisible(getBy("enterMsg"), 10);
        msgField.clear();
        msgField.sendKeys(message);
    }

    public boolean isSuccessMessageDisplayed() {
        WebElement success = Utils.waitForElementVisible(getBy("successAlert"), 10);
        logger.info("user waiting for Success message");
        return success.isDisplayed();
    }

    public boolean isErrorDisplayed(String key) {
    	 logger.info("validation for blank error");
        return Utils.waitForElementVisible(getBy(key), 10).isDisplayed();
        
    }

    public boolean isErrorGone(String key) {
    	 logger.info("validation for blank error gone");
        return Utils.waitForinvisibility(getBy(key), 10);
    }
    public void waitForFeedbackPopupToDisappear() {
       
    	 logger.info("waiting for pop-up");
        Utils.waitForinvisibility(getBy("feedbackModalHeader"), 20);
    }
    
    public String getSuccessMessageText() {
    	 logger.info("validation for Success message");
        return Utils.waitForElementVisible(getBy("successResult"), 10).getText().trim();
    }
    
  
}
