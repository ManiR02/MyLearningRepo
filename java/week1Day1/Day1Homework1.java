/*Facebook Login and Logout Functionality -- Selenium web driver code*/

package week1Day1;

import org.openqa.selenium.chrome.ChromeDriver;

public class Day1Homework1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		//Initialize driver
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		//Browser Maximize
		driver.manage().window().maximize();
		
		driver.get("http://www.facebook.com");
		driver.findElementById("email").sendKeys("king.manit@gmail.com");
		driver.findElementById("pass").sendKeys("*************");
		driver.findElementById("u_0_q").click();
		Thread.sleep(5000);
		driver.findElementById("userNavigationLabel").click();
		Thread.sleep(5000);
		driver.findElementByXPath("//*[@id='BLUE_BAR_ID_DO_NOT_USE']/div/div/div[1]/div/div/ul/li[16]/a/span/span").click();
		Thread.sleep(5000);
		driver.close();
			}

}
