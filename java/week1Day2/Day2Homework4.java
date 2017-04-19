////Automate IRCTC Registration until captcha
//https://www.irctc.co.in/eticketing/userSignUp.jsf 
//Tip: Add Thread.sleep(5000) after entering the pincode in IRCTC

package week1Day2;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Day2Homework4 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		//boolean by = true;
		//Launch the Browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		//Loading the IRTC User SignUp page
		driver.get("https://www.irctc.co.in/eticketing/userSignUp.jsf");

		//UserId input and availability check 
		driver.findElementById("userRegistrationForm:userName").sendKeys("ManiR1988");
		driver.findElementById("userRegistrationForm:checkavail").click();
		Thread.sleep(3000);

		WebElement useravail = driver.findElementById("userRegistrationForm:useravail");

		//UserId Status check  
		if (useravail.isDisplayed()){

			//Password and Confirm Password
			driver.findElementById("userRegistrationForm:password").sendKeys("Mani_02@test123");
			driver.findElementById("userRegistrationForm:confpasword").sendKeys("Mani_02@test123");

			//Security question selection dropdown  
			WebElement usersecq = driver.findElementById("userRegistrationForm:securityQ");
			Select qdropdown = new Select(usersecq);
			qdropdown.selectByVisibleText("What is your pet name?");

			//Security answer input
			driver.findElementById("userRegistrationForm:securityAnswer").sendKeys("Kalai");

			//Preferred Language selection dropdown  
			WebElement prfdLang = driver.findElementById("userRegistrationForm:prelan");
			Select ldropdown = new Select(prfdLang);
			ldropdown.selectByValue("hi");		

			//User Personal information 
			driver.findElementById("userRegistrationForm:firstName").sendKeys("Manikandan");
			driver.findElementById("userRegistrationForm:lastName").sendKeys("R");
			driver.findElementById("userRegistrationForm:gender:0").click();
			driver.findElementById("userRegistrationForm:maritalStatus:0").click();

			//Birth date selection dropdown  
			WebElement dateBirth = driver.findElementById("userRegistrationForm:dobDay");
			Select birthdate = new Select(dateBirth);
			birthdate.selectByValue("16");

			//Birth month selection dropdown  
			WebElement monthBirth = driver.findElementById("userRegistrationForm:dobMonth");
			Select birthmonth = new Select(monthBirth);
			birthmonth.selectByVisibleText("FEB");

			//Birth year selection dropdown  
			WebElement yearBirth = driver.findElementById("userRegistrationForm:dateOfBirth");
			Select birthyear = new Select(yearBirth);
			birthyear.selectByVisibleText("1988");

			//User Occupation selection dropdown  
			WebElement userOccp = driver.findElementById("userRegistrationForm:occupation");
			Select userjob = new Select(userOccp);
			userjob.selectByVisibleText("Professional");

			// User Email and Mobile number
			driver.findElementById("userRegistrationForm:email").sendKeys("mani.cse02@gmail.com");
			driver.findElementById("userRegistrationForm:mobile").sendKeys("9840159917");

			//User Nationality selection dropdown  
			WebElement userNat = driver.findElementById("userRegistrationForm:nationalityId");
			Select usernation = new Select(userNat);
			usernation.selectByValue("94");

			//User Address
			driver.findElementById("userRegistrationForm:address").sendKeys("7/1 A");

			//User Country selection dropdown  
			WebElement usercnty = driver.findElementById("userRegistrationForm:countries");
			Select userlocation = new Select(usercnty);
			userlocation.selectByValue("94");

			//Pincode
			driver.findElementById("userRegistrationForm:pincode").sendKeys("600015",Keys.TAB);
			Thread.sleep(5000);

			//State Auto populated based on Pincode selection

			//User City selection dropdown  
			WebElement userCity = driver.findElementById("userRegistrationForm:cityName");
			Select userlivecity = new Select(userCity);
			userlivecity.selectByVisibleText("Chennai");
			Thread.sleep(3000);

			//User Postoffice selection dropdown  
			WebElement userPost = driver.findElementById("userRegistrationForm:postofficeName");
			Select userPostal = new Select(userPost);
			userPostal.selectByVisibleText("Saidapet S.O (Chennai)");

			//User Landline number
			driver.findElementById("userRegistrationForm:landline").sendKeys("0442364464");
			Thread.sleep(3000);

			driver.close();
		}

		else {
			System.out.println("User Id already exists .... Please choose a different User Name..");
			driver.close();
		}
	}

}