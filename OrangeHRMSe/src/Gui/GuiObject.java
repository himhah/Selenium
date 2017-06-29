package Gui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class GuiObject {
		public String Name="";
		public String Value="";
		public String Type="";
		public WebElement Element = null;

		public void Set(String value) {
			Element.clear();
			Element.sendKeys(value);
		}
		public void Click() {
			Element.click();
		}
		public String Text() {
			return(Element.getText());
		}
		public void SelectByText(String value) {
			new Select(Element).selectByVisibleText(value);
		}
}
