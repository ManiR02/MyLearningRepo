/*Leaftaps.com Login and Logout -- Teastleaf exercise */ 

package week1Day1;

import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class LeaftapsLoginLogout {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Intialize
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver_64bit.exe");
		//Launch
		ChromeDriver driver =  new ChromeDriver();
		//FirefoxDriver driver =  new FirefoxDriver();
		
		//URL
		driver.get("http://leaftaps.com");
		//Maximize
		driver.manage().window().maximize();
		//Login credentials
		driver.findElementById("username").sendKeys("DemoSalesManager");
		driver.findElementById("password").sendKeys("crmsfa");
		driver.findElementByClassName("decorativeSubmit").click();
		driver.findElementByClassName("decorativeSubmit").click();
		driver.close();
	}

}
