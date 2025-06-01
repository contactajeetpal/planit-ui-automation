package com.planit.tests.utilities;
import org.openqa.selenium.By;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.planit.tests.base.BaseTest;

public class Utils extends BaseTest{
	//elementclickable
	public static WebElement waitForClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
	//visibilityofElement
	public static WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
    //wait for invisibility
	public static boolean waitForinvisibility(By locator,int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	//logger
	 public static Logger getLogger(Class<?> clazz) {
	        return LogManager.getLogger(clazz);
	    }
	 //read csv file
	 public static Object[][] readCSV(String filePath) {
	        List<Object[]> data = new ArrayList<>();
	        String line;

	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            br.readLine(); // Skip header line

	            while ((line = br.readLine()) != null) {
	                String[] fields = line.split(",", -1); // Include empty fields
	                data.add(fields);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return data.toArray(new Object[0][]);
	    }

}
