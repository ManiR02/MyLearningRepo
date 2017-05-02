package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.LeafTapsWrappers;

public class TC004_MergeLead extends LeafTapsWrappers {

	@BeforeClass
	public void setData(){
		browserName = "chrome";
		testCaseName = "Merge Lead";
		testDescription = "Merging two existing Lead IDs";
		category = "Regression";
		authors = "Manikandan";
		dataSheetName = "TC004";
	}


	@Test(dataProvider="fetchData")
	public void mergeLeads(String usn, String pass, String LeadID1, String LeadID2, String prmptMsg) {
		new LoginPage(driver, test)
		.enterUsername(usn)
		.enterPassword(pass)
		.clickLogin()
		.clickCRMSFA()
		.clickLeadlink()
		.clickMergeLeads()
		.clickLookupIcon1()
		.switchtoFindLeadsWnd()
		.enterLeadIDVal(LeadID1)
		.clickFindLeadsButton()
		.clickFirstResultingID()
		.switchtoMergeLeadsWnd()
		.clickLookupIcon2()
		.switchtoFindLeadsWnd()
		.enterLeadIDVal(LeadID2)
		.clickFindLeadsButton()
		.clickFirstResultingID()
		.switchtoMergeLeadsWnd()
		.clickMergeButton()
		.clickAcceptAlert()
		.clickFindLeads()
		.enterLeadID(LeadID1)
		.clickFindLeadButton()
		.verifyNoRecordsMsg(prmptMsg);
	}

}
