/**
 * 
 */
package object_models.top_right_nav_bar.quick_links;

import org.openqa.selenium.WebDriver;

import object_models.modules.common.ModuleElements;

/**
 * @author Steve Brown
 * 
 * All the quick links available in payroll.
 */
public class QuickLinksPayroll extends QuickLinks implements PersonnelLoader{
	private QuickLinkPrint print;	
	private QuickLinkPersonnel personnel;
	private QuickLinkAbsence absence;
	private QuickLinkTimeAttendance ta;
	private QuickLinkAppraisal appraisal;
	
	public QuickLinksPayroll(WebDriver driver) {
		super(driver);
		print = new QuickLinkPrint(driver);
		personnel = new QuickLinkPersonnel(driver);
		absence = new QuickLinkAbsence(driver);
		ta = new QuickLinkTimeAttendance(driver);
		appraisal = new QuickLinkAppraisal(driver);
	}

	public QuickLinkPrint getPrint() {
		return print;
	}

	public QuickLinkPersonnel getPersonnel() {
		return personnel;
	}

	public QuickLinkAbsence getAbsence() {
		return absence;
	}

	public QuickLinkTimeAttendance getTa() {
		return ta;
	}

	public QuickLinkAppraisal getAppraisal() {
		return appraisal;
	}

	@Override
	public ModuleElements load() {
		personnel.clickMe();
		return null;
	}
	
}
