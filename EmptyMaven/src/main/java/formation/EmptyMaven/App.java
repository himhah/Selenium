package formation.EmptyMaven;

import java.util.NoSuchElementException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hello world!
 *
 */
public class App 
{
	static WebDriver driver;
    public static void main( String[] args )
    {
    	
    	String baseUrl = "http://labacial/OrangeHRM";
    	driver = new FirefoxDriver();
    	driver.get(baseUrl);
    	
    	if (isElementPresent(By.id("txtUsername")))
    		System.out.print("Page de login affichée !\n");

    	driver.quit();
    }
    private static boolean isElementPresent(By by) {
        try {
          driver.findElement(by);
          return true;
        } catch (NoSuchElementException e) {
          return false;
        }
      }
}
