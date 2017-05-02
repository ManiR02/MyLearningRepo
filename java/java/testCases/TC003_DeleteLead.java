package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.FindLeadsPage;
import pages.LoginPage;
import wrappers.LeafTapsWrappers;

public class TC003_DeleteLead extends LeafTapsWrappers {

	@BeforeClass
	public void setData(){
		browserName = "chrome";
		testCaseName = "Delete Lead";
		testDescription = "Delete a Existing Lead data";
		category = "sanity";
		authors = "Manikandan";
		dataSheetName = "TC003";
	}
	@Test(dataProvider="fetchData")
	public void deleteLead(String usn, String pass,String pNum, String prmptMsg){
		String capturedLeadID = new LoginPage(driver, test)
				.enterUsername(usn)
				.enterPassword(pass)
				.clickLogin()
				.clickCRMSFA()
				.clickLeadlink()
				.clickFindLeads()
				.clickPhoneTab()
				.enterPNum(pNum)
				.clickFindLeadButton()
				.getCapturedLeadID();

		new FindLeadsPage(driver, test)
		.clickFirstResultLeadID()
		.clickDeleteButton()
		.clickFindLeads()
		.enterLeadID(capturedLeadID)
		.clickFindLeadButton()
		.verifyNoRecordsMsg(prmptMsg);
	}
}
