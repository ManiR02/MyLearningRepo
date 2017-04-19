/*5.Create contact in LeafTaps --> Login to LeafTaps,Click on CRMSFA link,Click Create contact,
Fill all mandatory fields,Click submit*/

package week1Day2;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Day2Homework5 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		//Launch Chrome Driver
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);

		//Loaded the URL
		driver.get("http://www.leaftaps.com/control/main");
		
		// User Login
		driver.findElementById("username").sendKeys("DemoSalesManager");
		driver.findElementById("password").sendKeys("crmsfa");
		driver.findElementByClassName("decorativeSubmit").click();
		
		//CRM Link Selection
		driver.findElementByLinkText("CRM/SFA").click();
		
		//Contact Creation
		driver.findElementByLinkText("Create Contact").click();
		driver.findElementById("firstNameField").sendKeys("Manikandan");
		driver.findElementById("lastNameField").sendKeys("Ravichandran");
		driver.findElementById("createContactForm_generalProfTitle").sendKeys("Mr.");
		
		//Preferred Currency selection DropDown
		WebElement currencySelect = driver.findElementById("createContactForm_preferredCurrencyUomId");
		Select preferCurrency = new Select(currencySelect);
		preferCurrency.selectByValue("INR");
		
		//Country Code and Contact mail
		driver.findElementById("createContactForm_primaryPhoneCountryCode").sendKeys("91");
		driver.findElementById("createContactForm_primaryEmail").sendKeys("mani.cse02@gmail.com");

		//Country selection DropDown -- Via Index value
		WebElement countrySelect = driver.findElementById("createContactForm_generalCountryGeoId");
		Select selectCountry = new Select(countrySelect);
		List <WebElement> all_countries = selectCountry.getOptions();
		System.out.println(all_countries.size());
		selectCountry.selectByIndex(all_countries.size()-1);

		//Submit action
		driver.findElementByClassName("smallSubmit").click();
		driver.close();

	}

}
