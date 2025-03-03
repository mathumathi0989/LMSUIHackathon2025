package hooks;

import org.openqa.selenium.WebDriver;

import pageObjects.BatchPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProgramPage;
import utilities.CommonPage;
import utilities.DriverFactory;
import utilities.ReadConfig;
import utilities.RunTimeData;

public class TestContext {

	private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
	private DriverFactory driverFactory;
	private ReadConfig readConfig;
	   private LoginPage loginPage;
	    private BatchPage batchPage;
	    private ProgramPage programPage;
	    private HomePage homePage;
	    private CommonPage commonPage;

	// Constructor initializes dependencies
	public TestContext() {
		this.driverFactory = new DriverFactory();
		this.readConfig = new ReadConfig();
		 this.loginPage = new LoginPage(this);  // Pass TestContext to pages
	        this.batchPage = new BatchPage(this);
	        this.homePage = new HomePage(this);
	        this.commonPage = new CommonPage(this);
	        this.programPage = new ProgramPage(this);
	}

	public void setDriver(WebDriver driver) {
		driverThreadLocal.set(driver); // Each thread gets its own WebDriver instance

	}

	  public WebDriver getDriver() {
	        WebDriver driver = driverThreadLocal.get();
	        if (driver == null) {
	            // Initialize the driver if it's null
	            String browserName = readConfig.getBrowserFromTestNG() != null ? 
	                                 readConfig.getBrowserFromTestNG() : readConfig.getbrowser();
	            String isHeadless = readConfig.getHeadlessOption();  // Ensure you get headless value here
	            driver = driverFactory.initialiseBrowser(browserName,isHeadless);
	            setDriver(driver); // Ensure driver is set to the context
	        }
	        return driver; // Retrieves WebDriver specific to the current thread
	    }
	  
	  // Method to access the page objects
	    public LoginPage getLoginPage() {
	        return loginPage;
	    }

	    public BatchPage getBatchPage() {
	        return batchPage;
	    }
	    public ProgramPage getProgramPage() {
	        return programPage;
	    }

	    public HomePage getHomePage() {
	        return homePage;
	    }

	    public CommonPage getCommonPage() {
	        return commonPage;
	    }
	    
	public DriverFactory getDriverFactory() {
		return driverFactory;
	}

	public void closeDriver() {
//		if (driverThreadLocal.get() != null) {
//			driverFactory.closeDriver();
//			  driverThreadLocal.get().quit();
//			driverThreadLocal.remove(); // Ensures WebDriver is removed when test is done
//		}
		 WebDriver driver = driverThreadLocal.get();
	        if (driver != null) {
	            driver.quit();
	            driverThreadLocal.remove();
	        }
	}

	public String getApplicationURL() {
		return readConfig.getApplicationURL();
	}

	   public void setScenarioData(String key, Object value) {
	        RunTimeData.setData(key, value);
	    }

	    public Object getScenarioData(String key) {
	        return RunTimeData.getData(key);
	    }

	    public void clearScenarioContext() {
	        RunTimeData.clearScenarioContext();
	    }
}
