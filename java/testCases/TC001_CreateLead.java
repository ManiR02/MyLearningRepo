package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;

import wrappers.LeafTapsWrappers;

public class TC001_CreateLead extends LeafTapsWrappers{
	
	@BeforeClass
	public void setData(){
		browserName = "chrome";
		testCaseName = "Create Lead";
		testDescription = "Test Case to Create a New Lead";
		category = "smoke";
		authors = "Manikandan";
		dataSheetName = "TC001";
		
	}
	
	
	
	@Test(dataProvider = "fetchData",threadPoolSize=3)
	public void createLead(String uName, String pwd, String CompName, String Fname, String Lname, String SourceVal){		
		new LoginPage(driver, test)
		.enterUsername(uName)
		.enterPassword(pwd)
		.clickLogin()
		.clickCRMSFA()
		.clickLeadlink()
		.clickCreateLeads()
		.enterCompName(CompName)
		.enterFirstName(Fname)
		.enterLastName(Lname)
		.selectSource(SourceVal)
		.clickCreateLead()
		.verifyCompName(CompName);

	}

}












