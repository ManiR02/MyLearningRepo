package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.LeafTapsWrappers;

public class MyLeadsPage extends LeafTapsWrappers{

	public MyLeadsPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;

		if(!verifyTitle("My Leads | opentaps CRM")){
			reportStep("This is not My Leads Page", "FAIL");
		}
	}
	public CreateLeadPage clickCreateLeads(){
		clickByLink("Create Lead");
		return new CreateLeadPage(driver, test);

	}
	public FindLeadsPage clickFindLeads(){
		clickByLink("Find Leads");
		return new FindLeadsPage(driver, test);
	}
	public MergeLeadPage clickMergeLeads(){
		clickByLink("Merge Leads");
		return new MergeLeadPage(driver, test);
	}
}















