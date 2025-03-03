package testRunner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import hooks.TestContext;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.ReadConfig;

@CucumberOptions(features = { "src/test/resources/features/" }, glue = { "hooks",
		"stepDefinitions" }, monochrome = true, tags = "@smoke", dryRun = false, plugin = { "pretty",
				"html:target/index.html", "json:target/cucumber-reports/Cucumber.json",
				"html:target/cucumber-reports/index.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
// "com.aventstack.chaintest.plugins.ChainTestCucumberListener:",
				"rerun:target/rerun.txt" })

public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	@BeforeClass
	@Parameters({ "browser", "headless" })
	public void browserFromTestNG(@Optional("chrome") String browser, @Optional("true") String headless) {
		System.out.println(">>> TestNG Parameter: Browser = " + browser + ", Headless = " + headless);
		ReadConfig config = new ReadConfig();
		config.setBrowserFromTestNG(browser);
		config.setHeadlessFromTestNG(headless);
	}
}