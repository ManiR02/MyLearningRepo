/*CheckBox Verification in isDisplayed() | isEnabled() | isSelected() */ 

package week1Day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckBoxValidation {

	public static void main(String[] args) {

		//Launch browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//Load URL
		driver.get("http://www.leafground.com/pages/checkbox.html");
		//Implicit Wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

		//Finding the check box element
		WebElement chkbox = driver.findElementByXPath("//*[@id='contentblock']/section/div[1]/input[1]");
		if (chkbox.isDisplayed() && chkbox.isEnabled()) //Check status is Verified && -> And operation (|| -> OR operation)
		{
			System.out.println("Check Box is Active and Displayed in Page");
			chkbox.click();
			if (chkbox.isSelected())
			{
				System.out.println("Check box is selected");
			}
			else 
			{
				System.out.println("Check box is not selected");
			}
		}
		else
		{
			System.out.println("Check is not active and not displayed");
		}

	}

}
