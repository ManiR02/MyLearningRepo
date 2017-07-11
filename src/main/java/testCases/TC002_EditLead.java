package testCases;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.LeafTapsWrappers;

public class TC002_EditLead extends LeafTapsWrappers {
	@BeforeClass
	public void setData(){
		browserName = "chrome";
		testCaseName = "Edit Lead";
		testDescription = "Edit a Existing Lead data";
		category = "sanity";
		authors = "Manikandan";
		dataSheetName = "TC002";
		
	}
	@Test(dataProvider="fetchData")
	public void editLead(String usn, String pass, String Fname, String CompName) throws InterruptedException{
		new LoginPage(driver, test)
		.enterUsername(usn)
		.enterPassword(pass)
		.clickLogin()
		.clickCRMSFA()
		.clickLeadlink()
		.clickFindLeads()
		.enterFName(Fname)
		.clickFindLeadButton()
		.clickFirstResultLeadID()
		.clickEditButton()
		.updateCompName(CompName)
		.clickUpdate()
		.verifyCompName(CompName);
	}

}
