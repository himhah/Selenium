package Action;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Gui.SeUtils;

public final class Login {

	public static void Connexion (String login, String pwd, String ExpectedMenu) {
		  SeUtils.Page("Authentication").Element("Username").Set(login);

		  SeUtils.Page("Authentication").Element("Password").Set(pwd);
		
		  SeUtils.Page("Authentication").Element("Login").Click();
		
	    try {
	      assertEquals(SeUtils.Page("Home").Element("WelcomeMenu").Text(), ExpectedMenu);
	    } catch (Error e) {
	    	SeUtils.logger.error(e.getMessage());
	    }

	}
	public static void Connexion (String dataFile, int lineNumber, String ExpectedMenu) {
		// Récupérer les données
		try {
			List logins = SeUtils.GetData ("D:\\DVLP\\OrangeHRMSe\\Data\\" + dataFile) ;
			
			Connexion ( ((String[])logins.get(lineNumber))[0], ((String[])logins.get(lineNumber))[1], ExpectedMenu);
		} catch (IOException e) {
	    	SeUtils.logger.error(e.getMessage());
		}


	}
	public static void Deconnexion () {
	    SeUtils.Page("Home").Element("WelcomeMenu").Click();
	    SeUtils.Page("Home").Element("Logout").Click();

	}
}
