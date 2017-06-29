package Gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.opencsv.CSVReader;

public final class SeUtils {
	public static WebDriver driver = null;
	public static List GuiPages = new ArrayList();
	public final static Logger logger = Logger.getLogger("OrangeHRM");
	private static String baseUrl;

	public static void Initialize() {
		  baseUrl = "http://localhost/OrangeHRM/";
		  SetDriver();
		  driver.get(baseUrl);
	}
	public static GuiPage Page(String page) {

		for (int i=0;i<GuiPages.size();i++) {
			if ( ((GuiPage)GuiPages.get(i)).Name.equals(page) ) {
				GuiPage p = (GuiPage)GuiPages.get(i);
				p.driver = driver;
				return (p);
			}
		}
		return(null);
		
	}
	public static void SetDriver () {
		if (driver==null)
		{
			driver = new FirefoxDriver();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    LoadGuiPages ();
		}
	}
	public static void LoadGuiPages () {
		String xmlDoc = "D:\\DVLP\\OrangeHRMSe\\DOM\\orangehrm.xml";
		File fXmlFile = new File(xmlDoc);
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("page");
			for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		           Element eElement = (Element) nNode;
		           
		           GuiPage page = new GuiPage();
		           page.Name = eElement.getElementsByTagName("name").item(0).getTextContent();
		           page.Type= eElement.getElementsByTagName("type").item(0).getTextContent();
		           GuiPages.add(page);
		           
		           NodeList objects = eElement.getElementsByTagName("object");
		           for (int k = 0; k < objects.getLength(); k++) {
		            		Node nobj = objects.item(k);
		            		Element objelem = (Element) nobj;
		            		
		            		GuiObject guiObject = new GuiObject();
		            		guiObject.Name = objelem.getElementsByTagName("name").item(0).getTextContent();
		            		guiObject.Type = objelem.getElementsByTagName("type").item(0).getTextContent();
		            		guiObject.Value = objelem.getElementsByTagName("value").item(0).getTextContent();
		            		page.GuiObjects.add(guiObject);
		            	}
		            	
		           }
		    }
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}

	public static List GetData (String dataFile) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(dataFile), ';');
		List lines = reader.readAll();
		reader.close();
		return (lines);
	}
}
