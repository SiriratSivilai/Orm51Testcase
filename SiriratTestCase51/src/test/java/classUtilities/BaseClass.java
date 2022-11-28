package classUtilities;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	private static WebDriver driver;
	
	private static Properties configFile;
	private static Properties userDataObject;

	static {

		try {
			String setupPath = Constants.CONFIGURATION_SETUP;
			String setupInput = (setupPath);

			FileInputStream configInput = new FileInputStream(setupPath);
			FileInputStream userDataInput = new FileInputStream(setupPath);

			configFile = new Properties();
			userDataObject = new Properties();

			configFile.load(configInput);
			userDataObject.load(userDataInput);

			configInput.close();
			userDataInput.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public static WebDriver getDriver() {
		if (driver == null) {
			switch (BaseClass.getSetup("Browser")) {
			
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
				
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
				
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
				
			case "safari":
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
				break;
				
			case "headless":
				break;

			}

			driver.get(BaseClass.getSetup("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
			Initializer.initialize();

		}
	
		
		return driver;

	}


	@AfterMethod(enabled = true)
	public static void tearDown() {
		
		if (driver != null) {
			driver.quit();
			driver = null;

		}
	}

	private static Properties configSetup;

	static {
		
		try {
			String setupPath = Constants.CONFIGURATION_SETUP;
			FileInputStream setupInput = new FileInputStream(setupPath);
			configSetup = new Properties();
			configSetup.load(setupInput);
			setupInput.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	    public static String getSetup(String keyName) {
	        return configSetup.getProperty(keyName);
	    }

	}

	

