/*Week2 : Homework 4*/
/*Leaftaps.com Duplicate Lead Test case through Wrapper Class*/

package week2Homework;

import org.junit.Test;

import wrappers.GenericWrappers;

public class DuplicateLeadWrappers extends GenericWrappers {

	@Test
		public void DuplicateLeadWrappers1() throws InterruptedException {
			invokeApp("Chrome", "http://leaftaps.com");
			enterById("username", "DemoSalesManager");
			enterById("password", "crmsfa");
			clickByClassName("decorativeSubmit");
			clickByLink("CRM/SFA");
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
