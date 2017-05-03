package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.LeafTapsWrappers;

public class FindLeadsPopupPage extends LeafTapsWrappers{

	public FindLeadsPopupPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;

		if(!verifyTitle("Find Leads")){
			reportStep("This is not Find Leads Pop-up Page", "FAIL");
		}

	}
	public FindLeadsPopupPage enterLeadIDVal(String data){
		enterByXpath("(//label[contains(text(),'Lead ID:')]/following::input)",data);
		return this;
	}

	public FindLeadsPopupPage clickFindLeadsButton() throws InterruptedException{
		clickByXpath("(//button[contains(text(),'Find Leads')])");
		Thread.sleep(3000);
		return this;
	}
	
	public FindLeadsPopupPage clickFirstResultingID(){
		clickByXpathNoSnap("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]");
		return this;
	}
	
	public MergeLeadPage switchtoMergeLeadsWnd(){
		switchToParentWindow();
		return new MergeLeadPage(driver, test);
	}

}














