package week2Day1;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

public class LeafWindowHandler {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Launch Browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver chrome = new ChromeDriver();
		
		//Implicit wait
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Maximise window
		chrome.manage().window().maximize();
		
		//Loading URL
		chrome.get("http://leaftaps.com/");
	    
		//Passing Login name
		chrome.findElementById("username").sendKeys("DemoSalesManager"); 	 
		
		//Passing Login password
		chrome.findElementById("password").sendKeys("crmsfa");
		
		//Clicking Login Button
		chrome.findElementByClassName("decorativeSubmit").click();

        //Clicking on CRM/SFA Link 
		chrome.findElementByLinkText("CRM/SFA").click();
		
		//Clicking on Leads
		chrome.findElementByLinkText("Leads").click();
		
		//Clicking on Merge Leads
		chrome.findElementByLinkText("Merge Leads").click();
		
		//Get handle of parent window
		String Parenthandle = chrome.getWindowHandle();
		
		String Childhandle2 = null;

		//Clicking on From Lead Icon
		chrome.findElementByXPath("(//img[@alt='Lookup'])[1]").click();
		
		//Get handle of Childhandle1		
		String Childhandle1 = chrome.getWindowHandle();

		//Get all the window handles
		 Set<String> allhandles = chrome.getWindowHandles();
		 int j = allhandles.size();
		 int i=1;
		 //Loop through each handle 
		 for (String eachhandle : allhandles) {
			 
			      System.out.println(allhandles.size());
			          
			 /*for (int i=1;i<=j;i++)
			 {
				 if(i==1)
				 {
					 System.out.println(chrome.getCurrentUrl());
					 System.out.println(chrome.getTitle());
					 System.out.println("First Iteration");
					 System.out.println(chrome.getWindowHandle());
				 }
				 else if (i ==2)
				 {
					
				 chrome.switchTo().window(eachhandle);			 
				 System.out.println("Second  Iteration");
				 System.out.println(chrome.getCurrentUrl());
				 System.out.println(chrome.getTitle());
				 System.out.println(chrome.getWindowHandle());
				 
				 
				 chrome.findElementByXPath("//label[contains(text(),'Lead ID:')]/following::input").sendKeys("10002");
				 chrome.findElementByXPath("//button[contains(text(),'Find Leads')]").click();
				 Thread.sleep(5000);
				 chrome.findElementByXPath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a").click();
				 chrome.switchTo().window(Parenthandle);
				 chrome.findElementByXPath("(//img[@alt='Lookup'])[2]").click();
				 Childhandle2 = chrome.getWindowHandle();
				 System.out.println(Childhandle2);
				 }
			 }*/
				 
				//alternate code
			   
					
				if(eachhandle.equals(Parenthandle)) 
				 {
					 System.out.println(chrome.getCurrentUrl());
					 System.out.println(chrome.getTitle());
					 System.out.println("First Iteration");
					 System.out.println(chrome.getWindowHandle());	
					 
				}
				else if (i ==2)
				{   
					
				 chrome.switchTo().window(eachhandle);
				 System.out.println(Childhandle1);
				 System.out.println("Second  Iteration");
				 System.out.println(chrome.getCurrentUrl());
				 System.out.println(chrome.getTitle());
				 System.out.println(chrome.getWindowHandle());
				 
				 
				 chrome.findElementByXPath("//label[contains(text(),'Lead ID:')]/following::input").sendKeys("10049");
				 chrome.findElementByXPath("//button[contains(text(),'Find Leads')]").click();
				 Thread.sleep(5000);
				 chrome.findElementByXPath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a").click();
				 
				 chrome.switchTo().window(Parenthandle);
				 chrome.findElementByXPath("(//img[@alt='Lookup'])[2]").click();
				 
				 
				 Childhandle2 = chrome.getWindowHandle();
				 
				 
				 System.out.println(Childhandle2);
				 chrome.switchTo().window(Childhandle2);
				 System.out.println(chrome.getCurrentUrl());
				 System.out.println(chrome.getTitle());
				 
				 
				}
				/*else 
				{
					chrome.switchTo().window(eachhandle);
					 System.out.println(Childhandle2);
					 System.out.println("Third  Iteration");
					 System.out.println(chrome.getCurrentUrl());
					 System.out.println(chrome.getTitle());
					 System.out.println(chrome.getWindowHandle());
					 
					 
					 chrome.findElementByXPath("//label[contains(text(),'Lead ID:')]/following::input").sendKeys("10050");
					 chrome.findElementByXPath("//button[contains(text(),'Find Leads')]").click();
					 Thread.sleep(5000);
					 chrome.findElementByXPath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a").click();
					 
					 chrome.switchTo().window(Parenthandle);
					 chrome.findElementByXPath("(//img[@alt='Lookup'])[2]").click();
					 
					 
					 Childhandle2 = chrome.getWindowHandle();
					 
					 
					 System.out.println(Childhandle2);
					 chrome.switchTo().window(Childhandle2);
					 System.out.println(chrome.getCurrentUrl());
					 System.out.println(chrome.getTitle());
				}*/
                   i=2;
				 
			 }
		 
			 

	}

}
