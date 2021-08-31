/**
 * 
 */
package object_models.panels.employee_creation;

/**
 * @author SteveBrown
 *
 */
public interface WizardMove {
	void goGack();
	WizardStepExecutor goNext();
}
