package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.LeafTapsWrappers;

public class MergeLeadPage extends LeafTapsWrappers{

	public MergeLeadPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;

		if(!verifyTitle("Merge Leads | opentaps CRM")){
			reportStep("This is not Merge Lead Page", "FAIL");
		}

	}

	public MergeLeadPage clickLookupIcon1(){
		clickByXpath("(//img[@alt='Lookup'])[1]");
		return this;
	}
	
	public FindLeadsPopupPage switchtoFindLeadsWnd(){
		switchToLastWindow();
		return new FindLeadsPopupPage(driver, test);
	}

	public MergeLeadPage clickLookupIcon2(){
		clickByXpath("(//img[@alt='Lookup'])[2]");	
		return this;
	}

	public MergeLeadPage clickMergeButton(){
		clickByLinkNoSnap("Merge");
		return this;
	}

	public ViewLeadPage clickAcceptAlert(){
		acceptAlert();
		return new ViewLeadPage(driver, test);
	}

}














