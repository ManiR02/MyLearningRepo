package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.LeafTapsWrappers;

public class ViewLeadPage extends LeafTapsWrappers{

	public ViewLeadPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;

		if(!verifyTitle("View Lead | opentaps CRM")){
			reportStep("This is not View Lead Page", "FAIL");
		}
	}

	public ViewLeadPage verifyCompName(String text){
		verifyTextContainsById("viewLead_companyName_sp", text);
		return this;
	}
	
	public ViewLeadPage verifyFName(String text){
		verifyTextById("viewLead_firstName_sp", text);
		return this;
	}

	public EditLeadPage clickEditButton(){
		clickByXpath("(//div[@class='frameSectionExtra']/a)[3]");
		return new EditLeadPage(driver, test);
	}
	public MyLeadsPage clickDeleteButton(){
		clickByXpath("//a[@class='subMenuButtonDangerous']");
		return new MyLeadsPage(driver, test);
	}
	
	public DuplicateLeadPage clickDuplicateLeadButton(){
		clickByLink("Duplicate Lead");
		return new DuplicateLeadPage(driver, test);
	}

	public FindLeadsPage clickFindLeads(){
		clickByLink("Find Leads");
		return new FindLeadsPage(driver, test);
	}
}














