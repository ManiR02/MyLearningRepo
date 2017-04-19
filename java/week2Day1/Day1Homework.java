package week2Day1;

import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day1Homework {
	
	public String getData(int value)
	{
		Scanner inputValue = new Scanner(System.in);
		System.out.println("Enter the Lead ID value for merge : ");
		value =inputValue.nextInt();
		return String.valueOf(value);
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		String inputData1, inputData2;
		//Object creation  for data input -- Step 1 
		Day1Homework leadInput = new Day1Homework(); 
		inputData1=leadInput.getData(0);
		inputData2=leadInput.getData(0);
		
		//Launch Chrome Browser-- Step 2
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);

		//Loaded the URL
		driver.get("http://www.leaftaps.com/control/main");

		// Username entered -- Step 3	
		driver.findElementById("username").sendKeys("DemoSalesManager");

		//User Password entered -- Step 4
		driver.findElementById("password").sendKeys("crmsfa");

		//User Login -- Step 5
		driver.findElementByClassName("decorativeSubmit").click();

		//CRM Link Selection -- Step 6
		driver.findElementByLinkText("CRM/SFA").click();

		//Leads Tab Selection -- Step 7
		driver.findElementByLinkText("Leads").click();

		//Merge Lead Selection -- Step 8
		driver.findElementByLinkText("Merge Leads").click();

		//Clicked From Lead Lookup -- Step 9
		driver.findElementByXPath("(//img[@alt='Lookup'])[1]").click();

		//Storing Parent Window Unique ID 
		String parentWnd = driver.getWindowHandle();

		//Handling all active windows and Move to next window -- Step 10
		Set<String> allwnds = driver.getWindowHandles();
		System.out.println("Total Active Windows : " + allwnds.size());
		for (String eachwnd : allwnds){
			System.out.println(eachwnd);// Printing Window handle ID for reference
			driver.switchTo().window(eachwnd);
		}

		System.out.println("****************************************************");

		//Lead ID entered in new Window -- Step 11
		driver.findElementByXPath("(//label[contains(text(),'Lead ID:')]/following::input)").sendKeys(inputData1);

		//Clicked Find Leads button -- Step 12
		driver.findElementByXPath("(//button[contains(text(),'Find Leads')])").click();
		Thread.sleep(3000); //Used for Page Refresh

		//First record link is clicked in resulting window -- Step 13
		driver.findElementByXPath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a").click();

		//Switching back to Parent Window -- Step 14
		driver.switchTo().window(parentWnd);

		//Click the To Lead lookup -- Step 15
		driver.findElementByXPath("(//img[@alt='Lookup'])[2]").click();

		//Handling all active windows and moved to new window  -- Step 16
		allwnds = driver.getWindowHandles();
		System.out.println("Total active Windows : "+allwnds.size());
		for (String eachwnd : allwnds){
			driver.switchTo().window(eachwnd);
			System.out.println(eachwnd);
		}

		//Lead ID entered in new Window -- Step 17
		driver.findElementByXPath("(//label[contains(text(),'Lead ID:')]/following::input)").sendKeys(inputData2);

		//Clicked Find Leads button -- Step 18
		driver.findElementByXPath("(//button[contains(text(),'Find Leads')])").click();
		Thread.sleep(3000);//Used for Page Refresh

		//First record link is clicked in resulting window -- Step 19
		driver.findElementByXPath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a").click();

		//Switching back to Parent Window -- Step 20
		driver.switchTo().window(parentWnd);

		//Clicking Merge button -- Step 21
		driver.findElementByLinkText("Merge").click();

		//Handling Alert -- Step 22
		Thread.sleep(3000); //Used to display the alert for 3 seconds
		driver.switchTo().alert().accept();

		//Clicked Find Leads link -- Step 23
		driver.findElementByLinkText("Find Leads").click();

		//Entered Lead ID -- Step 24
		driver.findElementByXPath("(//label[contains(text(),'Lead ID:')]/following::input)").sendKeys(inputData1);

		//Find Leads button is clicked -- Step 25
		driver.findElementByXPath("(//button[contains(text(),'Find Leads')])").click();

		System.out.println("****************************************************");

		//Verifying the Error Text -- Step 26
		WebElement errChk = driver.findElementByXPath("//div[contains(text(),'No records to display')]");
		if (errChk.getText().contains("No records to display"))
		{
			System.out.println("Error Text is matched");
			System.out.println(errChk.getText());
		}
		else{
			System.out.println("Error Text is not matched");
		}


		//Browser Quit
		driver.quit();
	}

}
