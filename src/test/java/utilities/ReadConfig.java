package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	private static String browserFromTestNG;
	private static String headlessFromTestNG; 

	public ReadConfig() {
		File src = new File("./src/test/resources/Properties/Config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}

	// get URL from config
	public String getApplicationURL() {
		String url = pro.getProperty("url");
		return url;
	}

	// get browser from config
	public String getbrowser() {
		String browser = pro.getProperty("browser");
		return browser;
	}

	
	// get Login credentials
	public String getUsername() {
		String username = pro.getProperty("LMSUserName");
		return username;
	}

	public String getPassword() {
		String pwd = pro.getProperty("LMSPassword");
		return pwd;
	}

	public String getDashboardurl() {
		String HomepageURL = pro.getProperty("DashBoardURL");
		return HomepageURL;
	}

	// read excelpath
	public String getExcelPath() {
		String path = pro.getProperty("excelPath");
		if (path != null)
			return path;
		else
			throw new RuntimeException("path not specified in the Configuration.properties file.");
	}
	
	public String getBrowserFromTestNG() {
		return browserFromTestNG;
	}

	public void setBrowserFromTestNG(String browser) {
		browserFromTestNG = browser;
	}


	// get headlessOption from config
	public String getHeadlessfromTestNG() {
		  return (headlessFromTestNG != null) ? headlessFromTestNG : "false";
	}
	
	public void setHeadlessFromTestNG(String isHeadless) {
		headlessFromTestNG = (isHeadless != null) ? isHeadless : "false";
	    System.out.println(">>> Debug: ReadConfig - Stored Headless Value = " + headlessFromTestNG);
}

	public String getHeadlessOption() {
		 return pro.getProperty("headless");
	}
	
}
