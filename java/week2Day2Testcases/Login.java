/*Week2 Classwork*/
/*Leaftaps.com Create Lead Class Work through Wrapper Class*/

package week2Day2Testcases;

import org.junit.Test;

import wrappers.GenericWrappers;



public class Login extends GenericWrappers {

	@Test
	public void login() throws Exception{
		
		invokeApp("chrome", "http://leaftaps.com");
		enterById("username", "DemoSalesManager");
		enterById("password", "crmsfa");
		clickByClassName("decorativeSubmit");
		//clickByClassName("decorativeSubmit");
		clickByLink("CRM/SFA");
		clickByLink("Create Lead");
		enterById("createLeadForm_companyName", "Plintron");
		enterById("createLeadForm_firstName", "Manikandan");
		enterById("createLeadForm_lastName", "R");
		//clickById("createLeadForm_dataSourceId");
		selectVisibileTextById("createLeadForm_dataSourceId","Conference");
		takeSnap();
		clickByName("smallSubmit");
		//takeSnap();
		closeBrowser();
		
		
		
	}
	
}
