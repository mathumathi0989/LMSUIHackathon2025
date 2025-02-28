package utilities;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    
    // Initialize WebDriver based on the browser name and headless mode
    public WebDriver initialiseBrowser(String browserName, String isHeadless) {
    	 WebDriver driver = driverThreadLocal.get();
    	    if (driver == null) {
    	    	 // Ensure no spaces are included and the string is parsed correctly
    	        boolean headless = Boolean.parseBoolean(isHeadless != null ? isHeadless.trim() : "false");
    	        
                System.out.println(">>> DriverFactory: Initializing Browser = " + browserName + ", Headless = " + headless);

    	        switch (browserName.toLowerCase()) {
    	            case "chrome":
    	                ChromeOptions chromeOptions = new ChromeOptions();
    	                if (headless) {
    	                    chromeOptions.addArguments("--headless=new"); // Enforce headless mode
    	                }
    	                chromeOptions.addArguments("--no-sandbox", "--disable-extensions", "--disable-gpu", "--remote-allow-origins=*");
    	                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
    	                driver = new ChromeDriver(chromeOptions);
    	                break;

    	            case "firefox":
    	                FirefoxOptions firefoxOptions = new FirefoxOptions();
    	                if (headless) {
    	                    firefoxOptions.addArguments("--headless");
    	                }
    	                driver = new FirefoxDriver(firefoxOptions);
    	                break;

    	            case "edge":
    	                driver = new EdgeDriver();
    	                break;

    	            case "safari":
    	                driver = new SafariDriver();
    	                break;

    	            default:
    	                throw new IllegalArgumentException("Browser not supported: " + browserName);
    	        }

    	        driver.manage().window().maximize();
    	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	        driverThreadLocal.set(driver);
    	    }
    	    return driver;
    }

    // Return the WebDriver for the current thread
    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    // Close the WebDriver and remove the instance from ThreadLocal
    public void closeDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit(); // Quit the WebDriver session
            driverThreadLocal.remove(); // Ensure WebDriver is removed when the test is done
        }
    }
}