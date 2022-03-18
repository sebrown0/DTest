/**
 * 
 */
package object_models.modules.common.nav.quick_links;

import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 * All the quick links available in personnel.
 */
public class QuickLinksPersonnel extends QuickLinks {
	private QuickLinkPrint print;
	private QuickLinkPayroll payroll;		
	private QuickLinkAbsence absence;
	private QuickLinkTimeAttendance ta;
	private QuickLinkAppraisal appraisal;
	
	public QuickLinksPersonnel(WebDriver driver) {
		super(driver);
		print = new QuickLinkPrint(driver);
		payroll = new QuickLinkPayroll(driver);				
		absence = new QuickLinkAbsence(driver);
		ta = new QuickLinkTimeAttendance(driver);
		appraisal = new QuickLinkAppraisal(driver);
	}
	
	public QuickLinkPayroll getPersonnel() {
		return payroll;
	}

	public QuickLinkPrint getPrint() {
		return print;
	}

	public QuickLinkPayroll getPayroll() {
		return payroll;
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
