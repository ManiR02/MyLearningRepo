package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.LeafTapsWrappers;

public class CreateLeadPage extends LeafTapsWrappers{

	public CreateLeadPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;

		if(!verifyTitle("Create Lead | opentaps CRM")){
			reportStep("This is not Create Lead Page", "FAIL");
		}

	}

	public CreateLeadPage enterCompName(String data){
		enterById("createLeadForm_companyName", data);
		return this;
	}

	public CreateLeadPage enterFirstName(String data){
		enterById("createLeadForm_firstName", data);
		return this;
	}


	public CreateLeadPage enterLastName(String data){
		enterById("createLeadForm_lastName", data);
		return this;
	}
	

	public CreateLeadPage selectSource(String value){
		//enterById("createLeadForm_lastName", data);
		selectVisibileTextById("createLeadForm_dataSourceId", value);
		return this;
	}

	public ViewLeadPage clickCreateLead(){
		clickByClassName("smallSubmit");
		return new ViewLeadPage(driver, test);
	}

}














