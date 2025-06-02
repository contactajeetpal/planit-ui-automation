# planit-ui-automation
Planit Test Automation Project
Please find below a detailed explanation of my solution for the automation technical assessment:

1. Base Class Design
Data Management: Reads dynamic input such as product names, quantities, and contact form data from a data.properties file, ensuring reusability and flexibility.

Environment Config: The jupiterConfig.properties file supports running the tests on different environments (e.g., pre-prod, prod) and browsers (Chrome, Safari).

Locator Handling: Centralized locator management via a dedicated locators.properties file allows for easy updates without changing the codebase.

2. Framework Utility
Developed reusable utilities (Utils.java) for WebDriver waits, logger setup, and element handling.

Implemented SoftAssert for comprehensive validations without stopping execution prematurely.

Logger integration captures detailed logs for debugging and audit via log4j.

3. Test Listener Implementation
Added a TestListener class implementing ITestListener for real-time logging of test events and failures in both console and file.

4. Test Scenarios
ContactFormTest.java

Test 1: Validates error messages on blank submission, fills mandatory fields, and revalidates.

Test 2: Submits valid form data and verifies the success message, executed 5 times for consistency.

ShoppingCartTest.java

Adds 2 Stuffed Frogs, 5 Fluffy Bunnies, and 3 Valentine Bears.

Navigates to the cart and validates unit price, subtotal, and total match the expected values dynamically.

5. CI/CD Integration
Pushed code to GitHub (main branch).

Integrated the project with Jenkins for scheduled test execution:

Configured a Freestyle project.

Pulled code via Git plugin using personal access token.

Set up Maven to build and run the suite periodically every 3 hours.

Configured post-build email notification.
