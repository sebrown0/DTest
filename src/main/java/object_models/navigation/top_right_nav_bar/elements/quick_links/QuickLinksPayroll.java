/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements.quick_links;

import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 * 
 * All the quick links available in payroll.
 */
public class QuickLinksPayroll extends QuickLinks {
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
	
}
