package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.LeafTapsWrappers;

public class FindLeadsPage extends LeafTapsWrappers{

	public FindLeadsPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;

		if(!verifyTitle("Find Leads | opentaps CRM")){
			reportStep("This is not Find Leads Page", "FAIL");
		}
	}

	public FindLeadsPage enterFName(String data){
		enterByXpath("(//label[contains(text(),'Lead ID:')]/following::input)[2]", data);
		return this;
	}

	public FindLeadsPage enterPNum(String data){
		enterByXpath("//input[@name='phoneNumber']",data);
		return this;
	}

	public FindLeadsPage enterLeadID(String data){
		enterByXpath("(//label[contains(text(),'Lead ID:')]/following::input)[1]",data);
		return this;
	}
	
	public FindLeadsPage enterEmailID(String data){
		enterByName("emailAddress", data);
		return this;
	}

	public FindLeadsPage clickFindLeadButton() throws InterruptedException{
		clickByXpath("//button[contains(text(),'Find Leads')]");
		Thread.sleep(3000);
		return this;
	}

	public ViewLeadPage clickFirstResultLeadID(){
		clickByXpathNoSnap("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]");
		return new ViewLeadPage(driver, test);
	}
	
	public FindLeadsPage clickPhoneTab(){
		clickByXpath("//span[text()='Phone']");
		return this;
	}
	
	public FindLeadsPage clickEmailTab(){
		clickByXpath("//span[contains(text(),'Email')]");
		return this;
	}

	public String getCapturedLeadID(){
		return getTextByXpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]");
	}

	public String getLeadFirstName(){
		String LeadFName = getTextByXpath("(//div[@class='x-grid3-cell-inner x-grid3-col-firstName'])[1]/a");
		return LeadFName;
	}
	
	public FindLeadsPage verifyNoRecordsMsg(String data){
		verifyTextByXpath("//div[text()='No records to display']", data);
		return this;
	}
}















