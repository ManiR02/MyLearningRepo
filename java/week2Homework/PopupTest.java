/*Week2 : Homework 6*/
/*Popuptest.com Multiple Window Handle test case through Wrapper Class*/

package week2Homework;

import org.junit.Test;

import wrappers.GenericWrappers;

public class PopupTest extends GenericWrappers {

	@Test
	public void PopupTest1(){
		invokeApp("chrome", "http://popuptest.com/");
		printWindowHandleID();
		printPageTitle();
		clickByLink("Multi-PopUp Test #2");
		switchToLastWindow();
		closeBrowser();
		switchToLastWindow();
		closeBrowser();
		switchToLastWindow();
		closeBrowser();
		switchToParentWindow();
		printPageTitle();
		clickByXpath("/html/body/table[2]/tbody/tr/td/form/input");
		switchToParentWindow();
		switchToLastWindow();
		closeBrowser();
		switchToParentWindow();
		printPageTitle();
		closeBrowser();
	}

}
