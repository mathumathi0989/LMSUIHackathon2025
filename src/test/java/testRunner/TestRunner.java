package testRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features = { "src/test/resources/features/Program.feature" }, 
				glue = { "hooks", "stepDefinitions" },
				monochrome = true, 
<<<<<<< HEAD

				//tags="@class",
                  tags="@TTLPH2-158",
=======
				tags="@doing",
>>>>>>> 63baf3fae368a8c1dc29db5b2a5522aa3bdfbf52

				dryRun = false, 
				//dryRun=true,
				plugin = { "pretty","html:target/index.html","json:target/cucumber-reports/Cucumber.json",
						"html:target/cucumber-reports/index.html",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				//"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				/*Commenting out report for now*/		
				 //"com.aventstack.chaintest.plugins.ChainTestCucumberListener:",
				"rerun:target/rerun.txt" // to record the failures
		// features={"@target/rerun.txt" } //to rerun only failed tests
		})

public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false) // The default thread count of the dataprovider in parallel mode is 10.
	public Object[][] scenarios() {
		return super.scenarios();
	}
}