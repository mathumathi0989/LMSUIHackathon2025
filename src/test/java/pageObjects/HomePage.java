package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import utilities.ElementUtil;


public class HomePage extends CommonPage {

	private WebDriver driver;
	private ElementUtil util;
	
	private By dashboardHeader = By.xpath("//div[normalize-space()='Dashboard']");
	private By dashboardTitile = By.xpath("//*[contains(text(),' Dashboard')]");
	private By lmsTitleBar = By.xpath("//mat-toolbar[@class='mat-toolbar mat-primary mat-toolbar-single-row ng-star-inserted']");
	private By lmsTitile =  By.xpath("//*[contains(text(),'LMS - Learning Management System')]");
	private By userLabel = By.xpath("//label[@id='mat-form-field-label-1']");
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		util = new ElementUtil(this.driver);
	}
	
	public boolean isDashboardHeaderVisible() {
		return util.isElementDisplayed(dashboardHeader);
	}
	public Point lmsTitleLocation() {
		WebElement title_element = driver.findElement(lmsTitleBar);
		return title_element.getLocation();
		

	}
	public String getDashboardText() {
		return util.getElementText(dashboardTitile);
		
		}
	public String getLMSPageTitleText() {
		return util.getElementText(lmsTitile);
	}
	public int lmsTitleLocationRightNavigation() {
		WebElement title_element = driver.findElement(lmsTitleBar);
		// Get the X-coordinate of the element
		int xCoordinate = title_element.getLocation().getX();
		// Get the width of the element;
		int width = title_element.getSize().getWidth();
		// Calculate the right-most position
        int rightMostLocation = xCoordinate + width;
		return rightMostLocation;
		

	}
	public void textSpellings() {
		  
		  String userText = driver.findElement(userLabel).getText();			  
		JLanguageTool languageTool = new JLanguageTool (new AmericanEnglish());
		  
		  try {
			  
			  List<RuleMatch> errors = languageTool.check(userText);  //perform spell check on the page text
			   if(errors.isEmpty()) {
				   System.out.println("No spelling mistake");
			   }else {
				   System.out.println("Spelling mistake found");
				   for (RuleMatch error : errors) {
					  
					   System.out.println("error on word:" + error.getLine() +", column" + error.getColumn() + ": " + error.getMessage());  
				   }
			   }
		  }catch (IOException e) {
			  
			  e.printStackTrace();
		  }		  
	  }
	
}
