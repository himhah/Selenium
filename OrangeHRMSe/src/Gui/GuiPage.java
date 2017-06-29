package Gui;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GuiPage {
	public WebDriver driver=null;
	public String Name="";
	public String Type="";
	public List GuiObjects = new ArrayList();
	
	public GuiObject Element(String guiObject) {

		for (int i=0;i<GuiObjects.size();i++) {
			if ( ((GuiObject)GuiObjects.get(i)).Name.equals(guiObject) ) {
				GuiObject obj = (GuiObject)GuiObjects.get(i);
				String[] parts = obj.Value.split(":");
			    By by=null;
			    switch (parts[0]) {
			    	case "id" :
			    		by = By.id(parts[1]);
			    		break;
			    	case "xpath" :
			    		by = By.xpath(parts[1]);
			    		break;
			    	case "css" :
			    		by = By.cssSelector(parts[1]);	
			    		break;
			    	case "name" :
			    		by = By.name(parts[1]);	
			    		break;
			    	case "link" :
			    		by = By.linkText(parts[1]);	
			    		break;
			    }
			    obj.Element = driver.findElement(by);
				return (obj);
			}
		}
		return(null);
		
	}
}
