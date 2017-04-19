package week1Day2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Question4_IRTC_dropdown_Egypt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Launch Browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		//WebPage Maximize and Implicit Wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);

		//Load url using GetUrl 
		driver.get("https://www.irctc.co.in/eticketing/userSignUp.jsf");

		//Using Select clause
		WebElement Condropdwn = driver.findElementById("userRegistrationForm:countries");
		Select CntyDropdown = new Select(Condropdwn);
		//To list all the options available in dropdown (Note :getOptions() returns all options belongs to select)  	
		List <WebElement> all_options = CntyDropdown.getOptions();
		//System.out.println("\n" + all_options);

		//Stored the total size of dropdown in a variable and printed 
		int totCount= all_options.size();
		System.out.println("Total number of country list : " + totCount);

		int a=1; // Declared a variable top get the second option  
		for (WebElement eachoption : all_options){ // For each to traverse over a collections 
			if (eachoption.getText().startsWith("E")){ // Returns true if County name starts with E
				a++;
				if (a==2){ //After reaching Second E country returns true 
					System.out.println(eachoption.getText()); // Prints the Country name
					eachoption.click();
					break; // Comes from Loop
				}
			}
		}
		driver.close();
	}
}