package hooks;

import org.openqa.selenium.WebDriver;

import utilities.DriverFactory;
import utilities.ReadConfig;
import utilities.RunTimeData;

public class TestContext {

	private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
	private DriverFactory driverFactory;
	private ReadConfig readConfig;

	// Constructor initializes dependencies
	public TestContext() {
		this.driverFactory = new DriverFactory();
		this.readConfig = new ReadConfig();

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
	  
	public DriverFactory getDriverFactory() {
		return driverFactory;
	}

	public void closeDriver() {
		if (driverThreadLocal.get() != null) {
			driverFactory.closeDriver();
			  driverThreadLocal.get().quit();
			driverThreadLocal.remove(); // Ensures WebDriver is removed when test is done
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
