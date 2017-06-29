package acial.selenium.dom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;

import acial.selenium.utils.Selenium;

public class UIObject {
		public String Name="";
		public String Value="";
		public String Type="";
		public By by = null;
		public WebElement Element = null;

		public void Set(String value) {
			Selenium.WaitForElement(this);
			try {
				Element.clear();
				Element.sendKeys(value);
				Selenium.logger.debug( "Element Set " + Name + ". " + by.toString() + " Value : " + value);
			}
			catch (Exception e) {
				Selenium.logger.error(e.getMessage());
			}
		}
		public void TypeEnter() {
			Selenium.WaitForElement(this);
			Element.sendKeys(Keys.RETURN);

			Selenium.logger.debug( "Element Key type " + Name + ". " + by.toString() + " RETURN");
		}

		public void Click() {
			try {
			Selenium.WaitForElementClickable(this);
			Element.click();

			Selenium.logger.debug( "Element Clicked " + Name + ". " + by.toString());
			Selenium.logger.debug("Current URL :" + Selenium.driver.getCurrentUrl());
		}
		catch (Exception e) {
			Selenium.logger.error(e.getMessage());
		}
		}
		public String Text() {
			try {
				Selenium.WaitForElement(this);

				Selenium.logger.debug( "Element Get Text " + Name + ". " + by.toString() + ". Texte : " + Element.getText());
				return(Element.getText());
			}
			catch (Exception e) {
				Selenium.logger.error(e.getMessage());
				return null;
			}
		}
		public String Value() {
			try {
				Selenium.WaitForElement(this);
				Selenium.logger.debug( "Element Get Value " + Name + ". " + by.toString() + ". Value : " + Element.getAttribute("value"));
				
				return(Element.getAttribute("value"));
			}
			catch (Exception e) {
				Selenium.logger.error(e.getMessage());
				return null;
			}
		}
		public void SelectByText(String value) {
			try {
				Selenium.WaitForElement(this);
				Selenium.logger.debug( "Element Select By Visible Text " + Name + ". " + by.toString() + ". Text : " + value);
				
				new Select(Element).selectByVisibleText(value);
			}
			catch (Exception e) {
				Selenium.logger.error(e.getMessage());
			}
		}
}
