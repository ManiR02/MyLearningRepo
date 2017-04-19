/*Week 3 - HomeWork 2 :: TEST CASE FOR MERGE ACCOUNT*/
package week3Homework;

import java.util.Scanner;

import org.junit.Test;

import project1.ProjectWrappers;

public class MergeAccounts extends ProjectWrappers{
	String accId1,accId2;
	
	public String getdata(int data){
		Scanner inputVal = new Scanner(System.in);
		data=inputVal.nextInt();
		return String.valueOf(data);
	}
	
	@Test
	public void accountMerge() throws InterruptedException{
		
		//GetData method is called to get the Input during Runtime
		MergeAccounts inputobj = new MergeAccounts();
		System.out.println("Enter the First Account ID :");
		accId1=inputobj.getdata(0);
		System.out.println("Enter the Second Account ID :");
		accId2=inputobj.getdata(0);
		
		login();
		clickByXpath("//a[text()='Accounts']");
		clickByLink("Merge Accounts");
		clickByXpath("(//img[@alt='Lookup'])[1]"); //clickByXpath("//input[@id='partyIdFrom']/following::img[1]");
		switchToLastWindow();
		enterByXpath("//label[text()='Account ID:']/following::input[1]", accId1);
		clickByXpath("//button[text()='Find Accounts']");
		Thread.sleep(2000);
		clickByXpathNoSnap("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a[1]");
		switchToParentWindow();
		clickByXpath("(//img[@alt='Lookup'])[2]");//clickByXpath("//input[@id='partyIdTo']/following::img[1]");
		switchToLastWindow();
		enterByXpath("//label[text()='Account ID:']/following::input[1]", accId2);
		clickByXpath("//button[text()='Find Accounts']");
		Thread.sleep(2000);
		clickByXpathNoSnap("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a[1]");
		switchToParentWindow();
		clickByXpathNoSnap("//a[@class='buttonDangerous']");
		Thread.sleep(2000);
		acceptAlert();
		clickByLink("Find Accounts");
		enterByXpath("//label[text()='Account ID:']/following::input[1]",accId1);
		clickByXpath("//button[text()='Find Accounts']");
		verifyTextByXpath("//div[@class='x-paging-info']", "No records to display");
		closeBrowser();
	}

}
