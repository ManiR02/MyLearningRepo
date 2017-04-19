/*Week 3 : Project Case 3*/

package project1testcase;

import org.junit.Test;

import project1.ProjectWrappers;

public class DeleteLead extends ProjectWrappers{

	@Test
	public void DeleteLead() throws InterruptedException{
		login();
		clickByLink("Leads");
		clickByLink("Find Leads");
		clickByXpath("//span[text()='Phone']");
		enterByXpath("//input[@name='phoneNumber']", "9800009917");
		clickByXpath("//button[contains(text(),'Find Leads')]");
		Thread.sleep(3000);
		String capturedID= getTextByXpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]");
		clickByXpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]");
		Thread.sleep(3000);
		clickByXpath("//a[@class='subMenuButtonDangerous']");
		clickByLink("Find Leads");
		enterByXpath("(//label[contains(text(),'Lead ID:')]/following::input)[1]", capturedID);
		clickByXpath("//button[contains(text(),'Find Leads')]");
		Thread.sleep(3000);
		verifyTextByXpath("//div[text()='No records to display']", "No records to display");
		closeBrowser();
	}
}
