package Action;

import Gui.SeUtils;

public final class Salarie {

	public static void Rechercher(String name) {
		SeUtils.Page("Menu").Element("PIM").Click();
		SeUtils.Page("Menu").Element("PIM_EmployeeList").Click();
		
		SeUtils.Page("PIM_EmployeeList").Element("EmployeeName").Set(name);
		SeUtils.Page("PIM_EmployeeList").Element("SearchBtn").Click();
	}
	public static void Ajouter() {
		SeUtils.Page("Menu").Element("PIM").Click();
		SeUtils.Page("Menu").Element("PIM_AddEmployee").Click();

		SeUtils.Page("PIM_AddEmployee").Element("FirstName").Set("Hassan");
		SeUtils.Page("PIM_AddEmployee").Element("LastName").Set("IMHAH");
		SeUtils.Page("PIM_AddEmployee").Element("EditSaveBtn").Click();
		// Edit
		SeUtils.Page("PIM_AddEmployee").Element("EditSaveBtn").Click();
		SeUtils.Page("PIM_AddEmployee").Element("Male").Click();

		SeUtils.Page("PIM_AddEmployee").Element("Nationality").SelectByText("Français");
		SeUtils.Page("PIM_AddEmployee").Element("MaritalStatus").SelectByText("Célibataire");

		SeUtils.Page("PIM_AddEmployee").Element("DateOfBirth").Set("1968-06-22");
		SeUtils.Page("PIM_AddEmployee").Element("EditSaveBtn").Click();
		
	}
	public static void Supprimer() {
	}
}
