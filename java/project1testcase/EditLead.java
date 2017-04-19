/*Week 3 : Project Case 2*/

package project1testcase;

import org.junit.Test;

import project1.ProjectWrappers;

public class EditLead extends ProjectWrappers {
	@Test
	public void EditLeadWrapper() throws InterruptedException {
		login();
		String compName="TCS";
		clickByLink("Leads");
		clickByLink("Find Leads");
		enterByXpath("(//label[contains(text(),'Lead ID:')]/following::input)[2]", "Dhoni");
		clickByXpath("//button[contains(text(),'Find Leads')]");
		Thread.sleep(3000);
		clickByXpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]");
		//Thread.sleep(3000);
		verifyTitle("View Lead | opentaps CRM");
		clickByXpath("(//div[@class='frameSectionExtra']/a)[3]");
		enterById("updateLeadForm_companyName", compName);
		clickByXpath("(//input[@class='smallSubmit'])[1]");
		//Thread.sleep(3000);
		verifyTextContainsByXpath("//span[@id='viewLead_companyName_sp']", compName);
		closeBrowser();
	}
}
