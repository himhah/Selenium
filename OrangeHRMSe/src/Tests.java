import org.testng.annotations.*;

import Action.Login;
import Action.Salarie;
import Gui.SeUtils;


import static org.testng.Assert.*;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.*;
public class Tests {
  	
  private boolean acceptNextAlert = true;
	
  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	  
      SeUtils.Initialize();
	  Login.Connexion ("Login.csv", 1, "Welcome Admin");
	  
  }

  
  @Test(priority=1)
  public void RechercherSalarie() throws Exception {
	  Salarie.Rechercher("NOM");
  }
  @Test(priority=1)
  public void AjouterSalarie() throws Exception {
	  Salarie.Ajouter();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	  Login.Deconnexion();
	  SeUtils.driver.quit();
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = SeUtils.driver.switchTo().alert();
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
