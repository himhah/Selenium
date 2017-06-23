package fr.acial.demo.FirstSample;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class OrangeHRM {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "D:/DVLP/drivers/chromedriver.exe");
	ChromeOptions options = new ChromeOptions();
	options.addArguments("disable-infobars");
	options.addArguments("--start-minimized");
    driver = new ChromeDriver(options);
    baseUrl = "http://labacial/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    // Login
    driver.get(baseUrl + "/OrangeHRM/symfony/web/index.php/auth/login");
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.id("txtUsername"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.id("txtUsername")).clear();
    driver.findElement(By.id("txtUsername")).sendKeys("admin");
    driver.findElement(By.id("txtPassword")).clear();
    driver.findElement(By.id("txtPassword")).sendKeys("test");
    driver.findElement(By.id("btnLogin")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.id("welcome"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

  
  
  
  }

  @Test
  public void testAjouterSalarie() throws Exception {
	  for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (isElementPresent(By.xpath("//a[@id='menu_pim_viewPimModule']/b"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']/b")).click();
		driver.findElement(By.id("menu_pim_addEmployee")).click();
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (isElementPresent(By.id("firstName"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("IMHAH");
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("Hassan");
		driver.findElement(By.id("btnSave")).click();
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (isElementPresent(By.xpath("//a[contains(@href, '/symfony/web/index.php/pim/viewPersonalDetails/empNumber')]"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}


  }

  @Test
  public void testSupprimerSalaries() throws Exception {
	  driver.findElement(By.id("menu_pim_viewPimModule")).click();
	  driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
	  for (int second = 0;; second++) {
	  	if (second >= 60) fail("timeout");
	  	try { if (isElementPresent(By.id("ohrmList_chkSelectAll"))) break; } catch (Exception e) {}
	  	Thread.sleep(1000);
	  }

	  driver.findElement(By.id("ohrmList_chkSelectAll")).click();
	  driver.findElement(By.id("btnDelete")).click();
	  
	  for (int second = 0;; second++) {
	  	if (second >= 60) fail("timeout");
	  	try { if (isElementPresent(By.id("dialogDeleteBtn"))) break; } catch (Exception e) {}
	  	Thread.sleep(1000);
	  }

	  driver.findElement(By.id("dialogDeleteBtn")).click();
  }
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	  for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (isElementPresent(By.id("welcome"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		driver.findElement(By.id("welcome")).click();
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (isElementPresent(By.linkText("Déconnexion"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		driver.findElement(By.linkText("Déconnexion")).click();

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
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
