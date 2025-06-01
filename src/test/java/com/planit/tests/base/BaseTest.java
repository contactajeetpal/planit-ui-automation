package com.planit.tests.base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.planit.tests.utilities.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public static Properties webConfig= new Properties();
	public static Properties locConfig= new Properties();
	public static Properties dataConfig= new Properties();
	public static FileReader readConfig;
	public static FileReader readLocation;
	public static FileReader readdata;
	
	
	
	private static final Logger logger = Utils.getLogger(BaseTest.class);
	//Config
	public static String getLocator(String key) {
        return locConfig.getProperty(key);
    }
	
	 public static By getBy(String key) {
	        return By.xpath(getLocator(key));
	    }
	 //Data
	 public static String getData(String key) {
		 return dataConfig.getProperty(key);
	 }
	
	
	@BeforeTest
	public void setup()
	{
	if(driver==null) {
		try {
		String locationForConfig=System.getProperty("user.dir") + "/src/test/resources/ConfigReader/jupiterConfig.properties";
		String locationForLocator=System.getProperty("user.dir") + "/src/test/resources/ConfigReader/locators.properties";
		String locationForData=System.getProperty("user.dir") + "/src/test/resources/ConfigReader/data.properties";
		FileReader readConfig= new FileReader(locationForConfig);
		FileReader readLocation= new FileReader(locationForLocator);
		FileReader readData=new FileReader(locationForData);
		webConfig.load(readConfig);
		locConfig.load(readLocation);
		dataConfig.load(readData);
		}catch (IOException e) {
			 e.printStackTrace();
	         throw new RuntimeException("Unable to load configuration file: " + e.getMessage());
		}
	}
	if(webConfig.getProperty("browser").equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new"); // use new headless mode
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--remote-debugging-port=9222");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		logger.info("Browser started in chrome headless mode");
		driver.get(webConfig.getProperty("AppUrl"));
		
	}else if(webConfig.getProperty("browser").equalsIgnoreCase("safari")) {
		WebDriverManager.safaridriver().setup();
		driver= new SafariDriver();
		driver.manage().window().maximize();
		logger.info("Browser started in safari");
		driver.get(webConfig.getProperty("AppUrl"));
	}

	}
	@AfterTest
	public void teardown() {
		driver.quit();
		System.out.print("Browser close:::");
		logger.info("Browser closed");
	}
}
