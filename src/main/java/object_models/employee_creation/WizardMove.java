/**
 * 
 */
package object_models.employee_creation;

/**
 * @author SteveBrown
 *
 */
public interface WizardMove {
	void goGack();
	WizardStepExecutor goNext();
}
