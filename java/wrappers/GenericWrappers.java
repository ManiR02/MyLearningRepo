package wrappers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class GenericWrappers implements Wrappers{

	RemoteWebDriver driver;
	int i=1;

	public void invokeApp(String browser, String url) {
		// TODO Auto-generated method stub
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}else{
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		takeSnap();
		System.out.println("The browser:" + browser + " launched successfully");
	}


	public void enterById(String idValue, String data) {
		// TODO Auto-generated method stub
		driver.findElementById(idValue).clear();
		driver.findElementById(idValue).sendKeys(data);	
		System.out.println("The data: "+data+" is entered successfully in input field :"+idValue);
		takeSnap();
	}


	public void enterByName(String nameValue, String data) {
		// TODO Auto-generated method stub
		driver.findElementByName(nameValue).sendKeys(data);
		System.out.println("The Attribute name of Text Field is "+nameValue+" and "+data+" is entered in Text box");
		takeSnap();
	}


	public void enterByXpath(String xpathValue, String data) {
		// TODO Auto-generated method stub
		driver.findElementByXPath(xpathValue).sendKeys(data);
		System.out.println("The Xpath of the Webelemet is "+xpathValue+" and "+data+" is enterted in specified Webelement");
		takeSnap();
	}


	public void verifyTitle(String title) {
		// TODO Auto-generated method stub
		String pageTitle = driver.getTitle();
		if (pageTitle.equals(title)){
			System.out.println("Given Page title is "+title+" is matched with the Current Page Title : "+pageTitle);
			takeSnap();
		}
		else{
			System.err.println("Given Page title is "+title+" is not matched with the Current Page Title : "+pageTitle);
			takeSnap();			
		}
	}


	public void verifyTextById(String id, String text) {
		// TODO Auto-generated method stub
		String verifyIdtext = driver.findElementById(id).getText();
		if (verifyIdtext.equals(text)){
			System.out.println("The "+id+" locator text value is "+verifyIdtext+" is matched with "+text);
			takeSnap();
		}
		else {
			System.err.println("The "+id+" locator text value is "+verifyIdtext+" is not matched with "+text);
			takeSnap();
		}
	}


	public void verifyTextByXpath(String xpath, String text) {
		// TODO Auto-generated method stub
		String textVerifyByXpath = driver.findElementByXPath(xpath).getText();
		if (textVerifyByXpath.equals(text))
		{
			System.out.println("The Specified Xpath : "+xpath+" text value is matched with "+text);
		}
		else {
			System.err.println("The Specified Xpath : "+xpath+" text value is not matched with "+text);
		}

	}


	public void verifyTextContainsByXpath(String xpath, String text) {
		// TODO Auto-generated method stub
		if (driver.findElementByXPath(xpath).getText().contains(text))
		{
			System.out.println("The Specified Xpath : "+xpath+" contains the text "+text);
			takeSnap();
		}
		else {
			System.err.println("The Specified Xpath : "+xpath+" not contains the text "+text);
			takeSnap();
		}
	}


	public void clickById(String id) {
		// TODO Auto-generated method stub
		driver.findElementById(id).click();
		System.out.println("The ID Webelement : "+id+ "is clicked.");
		takeSnap();
	}


	public void clickByClassName(String classVal) {
		driver.findElementByClassName(classVal).click();
		System.out.println("The Webelement with class Name : "+classVal+" is clicked.");
		takeSnap();
	}


	public void clickByName(String name) {
		// TODO Auto-generated method stub
		driver.findElementByName(name).click();
		System.out.println("The Name locator Webelement : "+name+" is clicked");
		takeSnap();

	}


	public void clickByLink(String name) {
		// TODO Auto-generated method stub
		driver.findElementByLinkText(name).click();
		System.out.println("The Link locator : "+name+" Webelement is Clicked");
		takeSnap();
	}


	public void clickByLinkNoSnap(String name) {
		// TODO Auto-generated method stub
		driver.findElementByLinkText(name).click();
		System.out.println("The Link locator : "+name+" Webelement is Clicked without taking Snapshot");
	}


	public void clickByXpath(String xpathVal) {
		// TODO Auto-generated method stub
		driver.findElementByXPath(xpathVal).click();
		System.out.println("The specified Xpath : "+xpathVal+" is clicked");
		//takeSnap();
	}


	public void clickByXpathNoSnap(String xpathVal) {
		// TODO Auto-generated method stub
		driver.findElementByXPath(xpathVal).click();
		System.out.println("The specified Xpath : "+xpathVal+" is clicked without taking Snapshot");
	}


	public String getTextById(String idVal) {
		// TODO Auto-generated method stub
		String getTextID =  driver.findElementById(idVal).getText();
		if (getTextID != null){
			System.out.println("The ID locator : "+idVal+" returns the text : "+getTextID);
			return getTextID; 
		}
		else {
			System.err.println("The ID locator : "+idVal+" failed to return any Text value");
			return null;
		}
	}


	public String getTextByXpath(String xpathVal) {
		// TODO Auto-generated method stub
		String getTextXpath =  driver.findElementByXPath(xpathVal).getText();
		if (getTextXpath != null){
			System.out.println("The Xpath locator : "+xpathVal+" returns the text : "+getTextXpath);
			return getTextXpath; 
		}
		else {
			System.err.println("The Xpath locator : "+xpathVal+" failed to return any Text value");
			return null;
		}
	}


	public void selectVisibileTextById(String id, String value) {
		Select ddwnVisText = new Select(driver.findElementById(id));
		ddwnVisText.selectByVisibleText(value);
		System.out.println("The ID Webelement (Dropdown) "+id+" is Clicked "+value+" is Selected" );
		// TODO Auto-generated method stub
		takeSnap();
	}


	public void selectIndexById(String id, int value) {
		// TODO Auto-generated method stub
		Select slctIndxbyId = new Select(driver.findElementById(id));
		slctIndxbyId.selectByIndex(value);
		System.out.println("The ID Webelement (Dropdown) "+id+" is Clicked "+value+" is Selected" );
		takeSnap();
	}


	public void switchToParentWindow() {
		// TODO Auto-generated method stub
		Set<String> swicthParentWnd = driver.getWindowHandles();
		System.out.println("Total Active Windows : "+swicthParentWnd.size());
		//takeSnap();
		for (String parentWnd : swicthParentWnd){
			driver.switchTo().window(parentWnd);
			System.out.println("Switched to Parent window and its Unique Handle ID : "+parentWnd);
			break;
		}
		//takeSnap();
	}


	public void switchToLastWindow() {
		// TODO Auto-generated method stub
		Set<String> switchLastWnd = driver.getWindowHandles();
		System.out.println("Total Active Windows : "+switchLastWnd.size());
		//takeSnap();
		for (String lastWnd : switchLastWnd){
			driver.switchTo().window(lastWnd);
		}
		String childWndId = driver.getWindowHandle();
		System.out.println("Switched to Last(Child) Window and its Unique Handle ID : "+childWndId);
		//takeSnap();
	}

	public void acceptAlert() {
		// TODO Auto-generated method stub
		//takeSnap();
		driver.switchTo().alert().accept();
		System.out.println("Displayed Alert is accepted");
	}


	public void dismissAlert() {
		// TODO Auto-generated method stub
		//takeSnap();
		driver.switchTo().alert().dismiss();
		System.out.println("Displayed Alert is accepted");

	}


	public String getAlertText() {
		// TODO Auto-generated method stub
		//takeSnap();
		String alertText = driver.switchTo().alert().getText();
		if (alertText != null){
			System.out.println("Displayed Alert Text is "+alertText);
			return alertText;
		}
		else{
			System.err.println("Alert Text is failed to fetch");
			return null;
		}
	}


	public void takeSnap() {
		// TODO Auto-generated method stub
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ScreenShot/Snap"+i+".jpeg");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		i++;
	}


	public void getNumberofFrames(String frametagname){
		// TODO Auto-generated method stub
		int total = 0, finalSize=0;
		//Finding the Outer Frame size in the page
		int count  = driver.findElementsByTagName(frametagname).size(); 
		//Printing the Outer Frame count 
		System.out.println("Outer Frame size : "+count);
		//Using for loop to switch into each Outer frame (Using Index) and to identify the inner frame size 
		for (int i=0;i<count;i++){
			driver.switchTo().frame(i); //Switch to each frame using Index
			total=driver.findElementsByTagName(frametagname).size(); //Identifying inner frames in each Outer Frame
			driver.switchTo().defaultContent(); //Switch to main web page
			System.out.println("Inner Frame "+i+" size is : " +total); //Printing Inner frame size in each outer frame
			finalSize += total; //Final Size of Inner Frame
		}
		count+=finalSize; //Sum of Outer and Inner Frame
		System.out.println("Overall Frames in the Page (Outer and Inner) : "+count);
	}

	public void printPageTitle(){
		// TODO Auto-generated method stub
		System.out.println("Current Page Title : "+driver.getTitle());
	}

	public void printWindowHandleID(){
		System.out.println("Current Window handle ID : "+driver.getWindowHandle());
	}


	public void closeBrowser() {
		// TODO Auto-generated method stub
		driver.close();
		System.out.println("The Active browser window is closed");
	}


	public void closeAllBrowsers() {
		// TODO Auto-generated method stub
		driver.quit();
		System.out.println("The Browser is Completely quit");

	}

}
