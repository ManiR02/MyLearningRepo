package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.FindLeadsPage;
import pages.LoginPage;
import wrappers.LeafTapsWrappers;

public class TC005_DuplicateLead extends LeafTapsWrappers{

	@BeforeClass
	public void setData(){
		browserName = "chrome";
		testCaseName = "Duplicate Lead";
		testDescription = "Duplicating a existing Lead using email ID";
		category = "Regression";
		authors = "Manikandan";
		dataSheetName = "TC005";
	}

	@Test(dataProvider="fetchData")
	public void duplicateLead(String usn, String pass, String email) throws InterruptedException{
		String capturedFname = new LoginPage(driver, test)
				.enterUsername(usn)
				.enterPassword(pass)
				.clickLogin()
				.clickCRMSFA()
				.clickLeadlink()
				.clickFindLeads()
				.clickEmailTab()
				.enterEmailID(email)
				.clickFindLeadButton()
				.getLeadFirstName();
		new FindLeadsPage(driver, test)
		.clickFirstResultLeadID()
		.clickDuplicateLeadButton()
		.verifyDuplicateLeadHeaderTitle()
		.clickCreateLeadButton()
		.verifyFName(capturedFname);


	}

}
