/*Week 3 - HomeWork 1 :: TEST CASE FOR CREATE ACCOUNT*/
package week3Homework;

import org.junit.Test;

import project1.ProjectWrappers;

public class CreateAccount extends ProjectWrappers{
	@Test	
	public void accountCreation() throws InterruptedException {
		String accName = "MandirSigh",accId, phoneNumber="9840222299",emailAddress="Test@gmail.com";
		login();
		clickByXpath("//a[text()='Accounts']");
		clickByXpath("//a[text()='Create Account']");
		enterById("accountName", accName);
		selectVisibileTextByXpath("//select[@name='industryEnumId']", "Computer Software");
		selectVisibileTextById("currencyUomId", "INR - Indian Rupee");
		selectVisibileTextById("dataSourceId", "Employee");
		selectVisibileTextById("marketingCampaignId", "eCommerce Site Internal Campaign");
		enterById("primaryPhoneNumber", phoneNumber);
		enterById("generalCity", "Chennai");
		enterById("primaryEmail", emailAddress);
		selectVisibileTextById("generalCountryGeoId", "India");
		Thread.sleep(2000);
		selectVisibileTextById("generalStateProvinceGeoId", "TAMILNADU");
		clickByClassName("smallSubmit");
		accId = getTextByXpath("//span[text()='Account Name']/following::span[1]").replaceAll("[^\\d]", "");
		clickByLink("Find Accounts");
		enterByXpath("//label[contains(text(),'Account ID')]/following::input[1]", accId);
		enterByXpath("//label[contains(text(),'Account ID')]/following::input[2]", accName);
		clickByXpath("//button[text()='Find Accounts']");
		Thread.sleep(2000);
		System.out.println("*************** Verification Section ***************");
		verifyTextByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a[1]", accId); //Verifying Account ID
		verifyTextByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-groupName']/a[1]", accName); //Verifying Account Name
		verifyTextContainsByXpath("(//div[@class='x-grid3-cell-inner x-grid3-col-formatedPrimaryPhone'])[1]", phoneNumber); //Verifying Phone number 
		verifyTextByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-primaryEmail']/a[1]", emailAddress); // Verifying email address 
		closeBrowser();	
	}

}
