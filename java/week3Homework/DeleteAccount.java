/*Week 3 - HomeWork 3 :: TEST CASE FOR DELETE ACCOUNT*/
package week3Homework;

import org.junit.Test;

import project1.ProjectWrappers;

public class DeleteAccount extends ProjectWrappers{
	@Test
	public void accountDelete() throws InterruptedException {
		String phonNum="9840222299";
		login();
		clickByXpath("//a[text()='Accounts']");
		clickByLink("Find Accounts");
		clickByXpath("//span[contains(text(),'Phone')]");
		enterByXpath("//input[@name='phoneNumber']",phonNum);
		clickByXpath("//button[text()='Find Accounts']");
		Thread.sleep(2000);
		String capturedAccID= getTextByXpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]");
		clickByXpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]");
		clickFindelementsByXpath("//img[@alt='Expire']");
		clickByLink("Find Accounts");
		enterByXpath("//label[text()='Account ID:']/following::input[1]",capturedAccID);
		clickByXpath("//button[text()='Find Accounts']");
		Thread.sleep(2000);
		clickByXpathNoSnap("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a[1]");
		verifyTextByXpath("//div[contains(text(),'No contact information on file')]", "No contact information on file");
		closeBrowser();
	}

}
