/*Week 3 : Project Case 5*/
/*Leaftaps.com Duplicate Lead Test case through Wrapper Class*/

package project1testcase;

import org.junit.Test;

import project1.ProjectWrappers;

public class DuplicateLeadWrappers extends ProjectWrappers {

	@Test
		public void DuplicateLeadWrappers1() throws InterruptedException {
			login();
			clickByLink("Leads");
			clickByLink("Find Leads");
			clickByXpath("//span[contains(text(),'Email')]");
			enterByName("emailAddress", "MS_Dhoni@gmail.com");
			clickByXpath("//button[contains(text(),'Find Leads')]");
			Thread.sleep(3000);
			String capturedText = getTextByXpath("(//div[@class='x-grid3-cell-inner x-grid3-col-firstName'])[1]/a");
			clickByXpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a");
			clickByLink("Duplicate Lead");
			verifyTextByXpath("//div[contains(text(),'Duplicate Lead')]", "Duplicate Lead");
			clickByClassName("smallSubmit");
			verifyTextById("viewLead_firstName_sp", capturedText);
			closeBrowser();
	}



}
