/*How to print the text that appears on a given text field?*/ 

package week1Day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Question5_ToPrintTextgiveninTextfield {

	public static void main(String[] args) {

		//Launch browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//Load URL
		driver.get("http://www.leafground.com/pages/Edit.html"); //Directly moving to console page

		//Implicit Wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

		//Finding the Text Box element and assigned to a webelement variable
		WebElement inputField = driver.findElementById("email");
		inputField.sendKeys("manikandan.rm@gmail.com"); //Sending key values to text box
		String txtField =  inputField.getAttribute("value"); // Getting the input from entered value  
		System.out.println(txtField); // Printing the entered text in conmsole
		driver.close();	
	}

}