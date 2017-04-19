/*Pre-assignment for learning conversion IDE Recorded action into Selenium Web driver*/   
/*Coded in Junit -- My First Selenium Webdriver Program*/

package ide;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class FirstSeleniumProgram {
  private WebDriver driver;
  private String baseUrl;
 // private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
	driver = new ChromeDriver();
    baseUrl = "http://testleaf.herokuapp.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testFirstSeleniumProgram() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.xpath("//img[@alt='Edit / Text Fields']")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("manikandan.r@plintron.com");
    try {
      assertEquals("TestLeaf", driver.findElement(By.name("username")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//input[@value='Append ']")).clear();
    driver.findElement(By.xpath("//input[@value='Append ']")).sendKeys("Append Success");
    driver.findElement(By.xpath("(//input[@name='username'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@name='username'])[2]")).sendKeys("");
    assertEquals("TestLeaf", driver.findElement(By.name("username")).getAttribute("value"));
    String myEmail = driver.findElement(By.id("email")).getAttribute("value");
    System.out.println(myEmail);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
	  String acceptNextAlert ;
    try {
      Alert alert = driver.switchTo().alert();
      acceptNextAlert = alert.getText();
      if (acceptNextAlert != null) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return acceptNextAlert;
    } finally {
      acceptNextAlert = "true";
    }
  }
}
