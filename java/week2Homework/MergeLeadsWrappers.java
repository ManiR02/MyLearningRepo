/*Week2 : Homework 3*/
/*Leaftaps.com Merge Lead Test case through Wrapper Class*/

package week2Homework;

import java.util.Scanner;

import org.junit.Test;

import wrappers.GenericWrappers;


//import week2Day1.Day1Homework;

public class MergeLeadsWrappers extends GenericWrappers{
	public String getData(int value)
	{
		Scanner inputValue = new Scanner(System.in);
		System.out.println("Enter the Lead ID value for merge : ");
		value =inputValue.nextInt();
		return String.valueOf(value);
	}

	@Test
	public void MergeLeadsWrappers1s() throws InterruptedException {
		String inputData1, inputData2;
		//Object creation  for data input -- Step 1 
		MergeLeadsWrappers leadInput = new MergeLeadsWrappers(); 
		inputData1=leadInput.getData(0);
		inputData2=leadInput.getData(0);

		invokeApp("chrome", "http://leaftaps.com");
		enterById("username", "DemoSalesManager");
		enterById("password", "crmsfa");
		clickByClassName("decorativeSubmit");
		clickByLink("CRM/SFA");
		clickByLink("Leads");
		clickByLink("Merge Leads");
		clickByXpath("(//img[@alt='Lookup'])[1]");
		switchToLastWindow();
		enterByXpath("(//label[contains(text(),'Lead ID:')]/following::input)", inputData1);
		clickByXpath("(//button[contains(text(),'Find Leads')])");
		Thread.sleep(3000);
		clickByXpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a");
		switchToParentWindow();
		clickByXpath("(//img[@alt='Lookup'])[2]");
		switchToLastWindow();
		enterByXpath("(//label[contains(text(),'Lead ID:')]/following::input)", inputData2);
		clickByXpath("(//button[contains(text(),'Find Leads')])");
		Thread.sleep(3000);
		clickByXpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a");
		switchToParentWindow();
		clickByLinkNoSnap("Merge");
		Thread.sleep(3000);
		getAlertText();
		acceptAlert();
		clickByLink("Find Leads");
		enterByXpath("(//label[contains(text(),'Lead ID:')]/following::input)", inputData1);
		clickByXpath("(//button[contains(text(),'Find Leads')])");
		//verifyTextContainsByXpath("//div[contains(text(),'No records to display')]", "No records to display");
		verifyTextByXpath("//div[contains(text(),'No records to display')]", "No records to display");
		closeBrowser();	
	}
}
