package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;

import wrappers.LeafTapsWrappers;

public class TC006_Login_negative extends LeafTapsWrappers{
	
	@BeforeClass
	public void setData(){
		browserName = "chrome";
		testCaseName = "Login Fialure";
		testDescription = "Test for Negative data";
		category = "sanity";
		authors = "gopi";
		dataSheetName = "TC006";
		
	}
	
	
	
	@Test(dataProvider = "fetchData")
	public void login(String uName, String pwd, String text){		
		new LoginPage(driver, test)
		.enterUsername(uName)
		.enterPassword(pwd)
		.clickLoginForFailure()
		.verifyErrorMsg(text);
		
		
		
		
	}

}












