package com.planit.tests.testcases;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.planit.tests.base.BaseTest;
import com.planit.tests.pages.ContactPage;



public class ContactFormTest extends BaseTest {
	
	
	SoftAssert softAssert = new SoftAssert();
	ContactPage contactPage = new ContactPage();

	@Test(priority=1)
	public void testEmptyFormValidation() {
	   contactPage.navigateToContact();
       contactPage.clickSubmit();
       // Assert errors present
       softAssert.assertTrue(contactPage.isErrorDisplayed("fornameError"), "Forename error message should be displayed.");
       softAssert.assertTrue(contactPage.isErrorDisplayed("foremailError"), "Email error message should be displayed.");
       softAssert.assertTrue(contactPage.isErrorDisplayed("formsgError"), "Message error message should be displayed.");

       // Fill required fields
       contactPage.fillMandatoryFields();

       // Assert errors gone
       softAssert.assertTrue(contactPage.isErrorGone("fornameError"), "Forename error should disappear.");
       softAssert.assertTrue(contactPage.isErrorGone("foremailError"), "Email error should disappear.");
       softAssert.assertTrue(contactPage.isErrorGone("formsgError"), "Message error should disappear.");

       
       softAssert.assertAll();
       
       
	}
	
	@Test(priority=2,invocationCount=5)
	public void testValidFormSubmission() {
		 
		contactPage.navigateToContact();
        contactPage.fillMandatoryFields();
        contactPage.clickSubmit();
        contactPage.waitForFeedbackPopupToDisappear();
        String fullMessage = contactPage.getSuccessMessageText();
        softAssert.assertTrue(fullMessage.contains("we appreciate your feedback"), "Feedback message is incorrect.");
        contactPage.clickBackBtn();
		

	}


}
