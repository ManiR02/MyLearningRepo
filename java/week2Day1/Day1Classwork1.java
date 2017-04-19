package week2Day1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day1Classwork1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		//Launch Browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		//Mazimize and Implictly Wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

		//Loading URL
		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt");

		//Finding the Frame in page with the ID 
		WebElement frm = driver.findElementById("iframeResult");
		//Switching to Frame
		driver.switchTo().frame(frm);
		driver.findElementByXPath("/html/body/button").click(); //Finding a button in Frame and Clicked 
		Thread.sleep(3000);

		//Handling Prompt Alert after clicking that button	
		Alert PrmptAlert = driver.switchTo().alert();
		PrmptAlert.sendKeys("Mani");//Passing values to Prompt Alert 
		PrmptAlert.accept();//Accepting the alert message

		//Verifying the Enter value is displayed in Page 	
		driver.findElementByXPath("//*[@id='demo']").getText().contains("Mani"); // It returns a boolean value 

		//IF Condition for Printing 
		if(driver.findElementByXPath("//*[@id='demo']").getText().contains("Mani"))
		{
			System.out.println("Text is Matched");
		}

		System.out.println("Text is not Matched");
		driver.close();
	}

}
