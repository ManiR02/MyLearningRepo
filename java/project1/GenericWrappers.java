package project1;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class GenericWrappers implements Wrappers{

	RemoteWebDriver driver;
	int i=1;

	public void invokeApp(String browser, String url) {
		try {
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
			System.out.println("The browser:" + browser + " launched successfully");
		} catch (WebDriverException e) {
			System.err.println("Browser is failed to Launch");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}finally{
			takeSnap();
		}
	}


	public void enterById(String idValue, String data) {
		try {
			driver.findElementById(idValue).clear();
			driver.findElementById(idValue).sendKeys(data);	
			System.out.println("The data: "+data+" is entered successfully in input field :"+idValue);
		} catch (NoSuchElementException e) {
			System.err.println("Webelement "+idValue+" is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();}
	}


	public void enterByName(String nameValue, String data) {
		try {
			driver.findElementByName(nameValue).sendKeys(data);
			System.out.println("The Attribute name of Text Field is "+nameValue+" and "+data+" is entered in Text box");
		} catch (NoSuchElementException e) {
			System.err.println("WebElement "+nameValue+" is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();}
	}


	public void enterByXpath(String xpathValue, String data) {
		try {
			driver.findElementByXPath(xpathValue).sendKeys(data);
			System.out.println("The Xpath of the Webelemet is "+xpathValue+" and "+data+" is enterted in specified Webelement");
		}  catch (NoSuchElementException e) {
			System.err.println("WebElement "+xpathValue+" is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();}
	}

	public void verifyTitle(String title) {
		try {
			String pageTitle = driver.getTitle();
			if (title.equalsIgnoreCase(pageTitle)){
				System.out.println("Given Page title is '"+title+"' is matched with the Current Page Title : "+pageTitle);
			}
			else{
				System.err.println("Given Page title is '"+title+"' is not matched with the Current Page Title : "+pageTitle);
			}
		} catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}finally{
			takeSnap();}
	}


	public void verifyTextById(String id, String text) {
		try {
			String verifyIdtext = driver.findElementById(id).getText();
			if (text.equalsIgnoreCase(verifyIdtext)){
				System.out.println("The "+id+" locator text value is '"+verifyIdtext+"' is matched with "+text);
			}
			else {
				System.err.println("The "+id+" locator text value is '"+verifyIdtext+"' is not matched with "+text);
			}
		}catch(NoSuchElementException e){
			System.err.println("WebElement "+id+" is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}
		catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();}
	}


	public void verifyTextByXpath(String xpath, String text) {
		try {
			String textVerifyByXpath = driver.findElementByXPath(xpath).getText();
			if (text.equalsIgnoreCase(textVerifyByXpath)){
				System.out.println("The Specified Xpath : "+xpath+" text value '"+textVerifyByXpath+"' is matched with "+text);
			}
			else {
				System.err.println("The Specified Xpath : "+xpath+" text value '"+textVerifyByXpath+"' is not matched with "+text);
			}
		}catch(NoSuchElementException e){
			System.err.println("WebElement "+xpath+" is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}


	public void verifyTextContainsByXpath(String xpath, String text) {
		try {
			String captureText = driver.findElementByXPath(xpath).getText();
			if (captureText.contains(text)){
				System.out.println("The Specified Xpath : "+xpath+" captured text '"+captureText+"' contains the text "+text);
			}
			else {
				System.err.println("The Specified Xpath : "+xpath+" captured text '"+captureText+"' not contains the text "+text);
			}
		}catch(NoSuchElementException e){
			System.err.println("WebElement "+xpath+" is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}


	public void clickById(String id) {
		try {
			driver.findElementById(id).click();
			System.out.println("The ID Webelement : "+id+ "is clicked.");
		} catch (NoSuchElementException e) {
			System.err.println("The Webelement ID : "+id+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}


	public void clickByClassName(String classVal) {
		try{
			driver.findElementByClassName(classVal).click();
			System.out.println("The Webelement with class Name : "+classVal+" is clicked.");
		}catch (NoSuchElementException e) {
			System.err.println("The Webelement class : "+classVal+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}

	public void clickByName(String name) {
		try{
			driver.findElementByName(name).click();
			System.out.println("The Name locator Webelement : "+name+" is clicked");
		}catch (NoSuchElementException e) {
			System.err.println("The Webelement class : "+name+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}


	public void clickByLink(String name) {
		try{
			driver.findElementByLinkText(name).click();
			System.out.println("The Link locator : "+name+" Webelement is Clicked");
		}catch (NoSuchElementException e) {
			System.err.println("The Webelement class : "+name+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}


	public void clickByLinkNoSnap(String name) {
		try{
			driver.findElementByLinkText(name).click();
			System.out.println("The Link locator : "+name+" Webelement is Clicked without taking Snapshot");
		}catch (NoSuchElementException e) {
			System.err.println("The Webelement class : "+name+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}
	}

	public void clickByXpath(String xpathVal) {
		try{
			driver.findElementByXPath(xpathVal).click();
			System.out.println("The specified Xpath : "+xpathVal+" is clicked");
		}catch (NoSuchElementException e) {
			System.err.println("The Webelement xpath : "+xpathVal+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}


	public void clickByXpathNoSnap(String xpathVal) {
		try{
			driver.findElementByXPath(xpathVal).click();
			System.out.println("The specified Xpath : "+xpathVal+" is clicked without taking Snapshot");
		}catch (NoSuchElementException e) {
			System.err.println("The Webelement xpath : "+xpathVal+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}
	}


	public void clickFindelementsByXpath(String xpath){
		try {
			int count;
			count=driver.findElementsByXPath(xpath).size();
			if (count!=0){
				System.out.println("Total Webelements count : "+count);
				for(int i=1;i<=count;i++){
					driver.findElementByXPath(xpath).click();
					System.out.println("Webelement "+i+" is Clicked");
				}
			}else {
				System.out.println("Total Webelements count : "+count);
			}
		} catch (NoSuchElementException e) {
			System.err.println("The Webelement xpath : "+xpath+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}


	public String getTextById(String idVal) {
		try {
			String getTextID =  driver.findElementById(idVal).getText();
			if (getTextID != null){
				System.out.println("The ID locator : "+idVal+" returns the text : "+getTextID);
				return getTextID; 
			}
			else {
				System.err.println("The ID locator : "+idVal+" failed to return any Text value");
				return null;
			}
		} catch (NoSuchElementException e) {
			System.err.println("The Webelement xpath : "+idVal+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");		
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
			return null;
		}finally{
			takeSnap();
		}
	}


	public String getTextByXpath(String xpathVal) {
		try{
			String getTextXpath =  driver.findElementByXPath(xpathVal).getText();
			if (getTextXpath != null){
				System.out.println("The Xpath locator : "+xpathVal+" returns the text : "+getTextXpath);
				return getTextXpath; 
			}
			else {
				System.err.println("The Xpath locator : "+xpathVal+" failed to return any Text value");
				return null;
			}
		}catch (NoSuchElementException e) {
			System.err.println("The Webelement xpath : "+xpathVal+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
			return null;
		}finally{
			takeSnap();
		}
	}


	public void selectVisibileTextById(String id, String value) {
		try{
			Select ddwnVisText = new Select(driver.findElementById(id));
			ddwnVisText.selectByVisibleText(value);
			System.out.println("The ID Webelement (Dropdown) "+id+" is Clicked and "+value+" is Selected" );
		}catch (NoSuchElementException e) {
			System.err.println("The Webelement Id : "+id+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}


	public void selectIndexById(String id, int value) {
		try{
			Select slctIndxbyId = new Select(driver.findElementById(id));
			slctIndxbyId.selectByIndex(value);
			System.out.println("The ID Webelement (Dropdown) "+id+" is Clicked and "+value+" is Selected" );
		}catch (NoSuchElementException e) {
			System.err.println("The Webelement Id : "+id+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}

	public void selectVisibileTextByXpath(String xpath, String value) {
		try{
			Select ddwnVisTextXpath = new Select(driver.findElementByXPath(xpath));
			ddwnVisTextXpath.selectByVisibleText(value);
			System.out.println("The Xpath Webelement (Dropdown) "+xpath+" is Clicked and "+value+" is Selected" );
		}catch (NoSuchElementException e) {
			System.err.println("The Webelement Id : "+xpath+ " is unable to locate");
			throw new RuntimeException("Unexpected Error Occured during RunTime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}

	public void switchToParentWindow() {
		try{
			Set<String> swicthParentWnd = driver.getWindowHandles();
			System.out.println("Total Active Windows : "+swicthParentWnd.size());
			for (String parentWnd : swicthParentWnd){
				driver.switchTo().window(parentWnd);
				System.out.println("Switched to Parent window and its Unique Handle ID : "+parentWnd);
				break;
			}
		}catch (NoSuchWindowException e) {
			System.err.println("No such Window is found");
			throw new RuntimeException("Unexpected Error while switching to Parent Window");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}

	}


	public void switchToLastWindow() {
		try{
			Set<String> switchLastWnd = driver.getWindowHandles();
			System.out.println("Total Active Windows : "+switchLastWnd.size());
			for (String lastWnd : switchLastWnd){
				driver.switchTo().window(lastWnd);
			}
			String childWndId = driver.getWindowHandle();
			System.out.println("Switched to Last(Child) Window and its Unique Handle ID : "+childWndId);
		}catch (NoSuchWindowException e) {
			System.err.println("No such Window is found");
			throw new RuntimeException("Unexpected Error while switching to Last Window");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}

	}


	public void acceptAlert() {
		try{
			driver.switchTo().alert().accept();
			System.out.println("Displayed Alert is Accepted");
		}catch (NoAlertPresentException e) {
			System.err.println("No Alert is displayed in Page");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}

	public void dismissAlert() {
		try{
			driver.switchTo().alert().dismiss();
			System.out.println("Displayed Alert is Rejected");
		}catch (NoAlertPresentException e) {
			System.err.println("No Alert is displayed in Page");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}

	public String getAlertText() {
		try{
			String alertText = driver.switchTo().alert().getText();
			if (alertText != null){
				System.out.println("Displayed Alert Text is "+alertText);
				return alertText;
			}
			else{
				System.err.println("Alert Text is failed to fetch");
				return null;
			}
		}catch (NoAlertPresentException e) {
			System.err.println("No Alert is displayed in Page");
			throw new RuntimeException("Unexpected Error while switching to Last Window");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
			return null;
		}
	}


	public void takeSnap() {
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ScreenSnap/Snap"+i+".jpeg");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {

			e.printStackTrace();
		}
		i++;
	}


	public void getNumberofFrames(String frametagname){
		int total = 0, finalSize=0;
		//Finding the Outer Frame size in the page
		try{
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
		}catch (NoSuchFrameException e) {
			System.err.println("No Frame is found");
			throw new RuntimeException("Unexpected error ocurred in Runtime");
		}catch (WebDriverException e){
			System.err.println("Unexpected Error occured in the Browser");
		}finally{
			takeSnap();
		}
	}






	public void printPageTitle(){
		System.out.println("Current Page Title : "+driver.getTitle());
	}


	public void printWindowHandleID(){
		System.out.println("Current Window handle ID : "+driver.getWindowHandle());
	}


	public void closeBrowser() {
		try{
			driver.close();
			System.out.println("The Active browser window is closed");
		} catch (Exception e) {
			System.out.println("Unexpected error Ocurred : " + e);
		}
	}

	public void closeAllBrowsers() {
		try{
			driver.quit();
			System.out.println("The Browser is Completely quit");
		} catch (Exception e) {
			System.out.println("Unexpected error Ocurred : " + e);
		}

	}

}
