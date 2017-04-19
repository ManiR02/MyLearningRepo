package week2Day1;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day1Classwork2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

		driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
		driver.findElementByLinkText("Contact Us").click();

		// getwindowHandle (Currently 1 window alone opened)
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());

		//getwindowHandles
		Set<String> allHandles = driver.getWindowHandles();

		//Printing Total number windows opened
		System.out.println(allHandles.size());
		
		//Using For each loop : Switching to each window and printing the Unique value of each window
		for (String eachHandle : allHandles){
			System.out.println(eachHandle);
			driver.switchTo().window(eachHandle);
		}
		//Printing Each Window Title and URL 
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		
		//Closing the current active Window
		driver.close();
		
		//Again taking a count for number of active window
		Set<String> singlewind = driver.getWindowHandles();
		//Through For each : Switching to another window 
		for (String eachHandle1 : singlewind){
			System.out.println(eachHandle1);
			driver.switchTo().window(eachHandle1);
		}
		
		//After switching to IRTC main window clicking Login button without credentials
		driver.findElementById("loginbutton").click();
		
		Thread.sleep(3000);
		//Handling Alerts
		Alert simpleAlert = driver.switchTo().alert();
		//Received Alert Text 
		System.out.println(simpleAlert.getText());
		//Accepted Alert 
		simpleAlert.accept();
		driver.close();
		/*Note : driver.quit(); -- close the all windows 
		and driver.close(); -- Will close current active window*/
	}

}
