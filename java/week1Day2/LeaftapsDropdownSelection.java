/*Leaftaps.com Dropdown selection (Index | Value | VisiblebyText | All Options) -- Teastleaf exercise */
/* All options and For each loop are not tested yet */

package week1Day2;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class LeaftapsDropdownSelection {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		//Intialize
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver_64bit.exe");
		//Launch
		ChromeDriver driver =  new ChromeDriver();
		//FirefoxDriver driver =  new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//URL
		driver.get("http://leaftaps.com");
		//Maximize
		driver.manage().window().maximize();
		//Login credentials
		driver.findElementById("username").sendKeys("DemoSalesManager");
		driver.findElementById("password").sendKeys("crmsfa");
		driver.findElementByClassName("decorativeSubmit").click();
		driver.findElementByLinkText("CRM/SFA").click();
		driver.findElementByLinkText("Create Lead").click();
		driver.findElementById("createLeadForm_companyName").sendKeys("Plintron");
		driver.findElementById("createLeadForm_firstName").sendKeys("Manikandan");

		WebElement dd = driver.findElementById("createLeadForm_dataSourceId"); 
		Select Dropdown = new Select(dd);
		Dropdown.selectByVisibleText("Cold Call");
		Thread.sleep(3000);
		Dropdown.selectByValue("LEAD_DIRECTMAIL");
		Thread.sleep(3000);
		Dropdown.selectByIndex(2);
		Thread.sleep(3000);
		
		Dropdown.getOptions();
		
		//driver.findElementByClassName("decorativeSubmit").click();
		driver.close();
	}

}
