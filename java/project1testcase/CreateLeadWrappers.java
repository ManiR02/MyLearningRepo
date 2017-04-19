/*Week 3 : Project Case 1*/

package project1testcase;

import org.junit.Test;

import project1.GenericWrappers;
import project1.ProjectWrappers;

public class CreateLeadWrappers extends ProjectWrappers{

@Test
	public void CreateLeadWrappers() throws InterruptedException{
		login();
		clickByLink("Create Lead");
		enterById("createLeadForm_companyName", "UST Global");
		enterById("createLeadForm_firstName", "Dhoni");
		enterById("createLeadForm_lastName", "Mahidher Singh");
		selectVisibileTextById("createLeadForm_dataSourceId", "Employee");
		selectIndexById("createLeadForm_marketingCampaignId", 3);
		enterByName("firstNameLocal", "Thala");
		enterByName("lastNameLocal", "Dhoni");
		enterByName("personalTitle", "Mr.");
		enterById("createLeadForm_generalProfTitle", "Captain");
		enterById("createLeadForm_departmentName", "Sales");
		enterByXpath("//*[@id='createLeadForm_annualRevenue']", "500000");
		selectVisibileTextById("createLeadForm_currencyUomId", "AMD - Armenian Dram");
		selectIndexById("createLeadForm_industryEnumId", 3);
		enterByName("numberEmployees", "9450");
		selectVisibileTextById("createLeadForm_ownershipEnumId","Partnership");
		enterByXpath("//*[@id='createLeadForm_sicCode']", "D878");
		enterById("createLeadForm_tickerSymbol", "Euro");
		enterById("createLeadForm_description", "Hi this Thala Dhoni");
		enterById("createLeadForm_importantNote", "Created Contact");
		enterById("createLeadForm_primaryPhoneCountryCode", "+91");
		enterById("createLeadForm_primaryPhoneAreaCode","524");
		enterById("createLeadForm_primaryPhoneNumber", "9800009917");
		enterById("createLeadForm_primaryPhoneExtension", "7560");	
		enterById("createLeadForm_primaryPhoneAskForName", "Kholi");
		enterById("createLeadForm_primaryEmail", "MS_Dhoni@gmail.com");
		enterById("createLeadForm_generalToName", "CSK");
		enterById("createLeadForm_generalAttnName", "Thala MSD");
		enterById("createLeadForm_generalAddress1", "Chennai Super Kings");
		enterById("createLeadForm_generalAddress2", "Saidapet");
		enterById("createLeadForm_generalCity", "Chennai");
		selectVisibileTextById("createLeadForm_generalCountryGeoId", "India");
		Thread.sleep(2000);
		selectVisibileTextById("createLeadForm_generalStateProvinceGeoId", "TAMILNADU");
		enterById("createLeadForm_generalPostalCode", "600015");
		enterById("createLeadForm_generalPostalCodeExt", "91");
		clickByClassName("smallSubmit");
		verifyTextById("viewLead_firstName_sp", "Dhoni");
		verifyTitle("View Lead | opentaps CRM");
		verifyTextContainsByXpath("//*[@id='viewLead_lastName_sp']", "Mahidher Singh");
		closeBrowser();
	}
}