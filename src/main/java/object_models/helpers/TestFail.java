/**
 * 
 */
package object_models.helpers;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Steve Brown
 *
 */
public class TestFail implements Runnable{
	private String msg;
	
	public TestFail(String msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		fail(msg);		
	}

}
