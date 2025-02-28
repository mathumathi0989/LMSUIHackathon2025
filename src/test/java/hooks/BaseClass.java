package hooks;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import utilities.ExcelReader;
import utilities.Log;
import utilities.ReadConfig;
import utilities.RunTimeData;

public class BaseClass {
	 private static TestContext context;
	    private ReadConfig readConfig;

	    public BaseClass(TestContext Context) {
	        BaseClass.context = Context;
	        readConfig = new ReadConfig();
	    }

	    @BeforeAll
	    public static void externalFIleOrAppSetUp() throws Exception {
	        
			String excelPath = new ReadConfig().getExcelPath();
	        System.out.println("Excel file path = " + excelPath); 
	        if(excelPath != null) {
	        	
	        	ExcelReader.openExcel(excelPath);
	    	    System.out.println("Excel file opened successfully.");
	        	}  
	        else System.out.println("No excel path found");
	        }
			

	    @Before("not @login") // Ensure this is only applied to scenarios that do not include the @login tag
	    public void setUp() {
	        Log.logInfo("Initializing WebDriver");

	        // Get the browser name (from TestNG or the default config)
	        String browserName = readConfig.getBrowserFromTestNG() != null ? 
	                             readConfig.getBrowserFromTestNG() : readConfig.getbrowser();

	        // Retrieve headless value from TestNG parameters or default config
	        String isHeadless = readConfig.getHeadlessfromTestNG() != null ? 
                    readConfig.getHeadlessfromTestNG() : readConfig.getHeadlessOption();
	        		

	        // Debug: Log the headless value
	        System.out.println(">>> Debug: Retrieved Headless Value in setUp() = " + isHeadless);

	        // If the headless value is null or empty, default to "false"
	        if (isHeadless == null || isHeadless.isEmpty()) {
	            isHeadless = "false";
	        }

	        // Log the final browser and headless configuration
	        System.out.println(">>> BaseClass: Browser = " + browserName + ", Headless = " + isHeadless);

	        // Initialize the WebDriver
	        WebDriver driver = context.getDriverFactory().initialiseBrowser(browserName, isHeadless);
	        context.setDriver(driver);

	        // Navigate to the application URL
	        Log.logInfo("Navigating to: " + readConfig.getApplicationURL());
	        context.getDriver().get(readConfig.getApplicationURL());
	    }

		@After
		public void tearDown(Scenario scenario) {
			Log.logInfo("Screenshots for failed");
			if (scenario.isFailed()) {
				// Use context.getDriver() to take a screenshot
				//Screenshot.takeScreenshot(context.getDriver(), scenario.getName());
				final byte[] screenshot = ((TakesScreenshot) context.getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Myscreenshot");
				Allure.addAttachment("Myscreenshot",
						new ByteArrayInputStream(((TakesScreenshot) context.getDriver()).getScreenshotAs(OutputType.BYTES)));
			}
			Log.logInfo("Closing WebDriver");
			context.closeDriver();

		}

		@AfterAll
		public static void externalFIleOrAppTearDown() {
			try {
				
				//empty dataMap
				RunTimeData.emptyDataMap();
				
				// Close the Excel file
				ExcelReader.closeExcel();
				System.out.println("Excel file closed successfully.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// to attach screeshots in allure
			@Attachment(value = "Screenshot", type = "image/png")
			public byte[] attachScreenshot(WebDriver driver) {
				// Capture the screenshot and return it as bytes
				return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			}
}
