/**
 * 
 */
package app.test_runner;

/**
 * @author SteveBrown
 *
 */
public class TestSuiteClass  implements TestSuiteGetter<Class<?>[]> {
  private String moduleName;
//  private List<TestSuite> testSuites;
//  private ModuleData moduleData;  
  private String modulePath;
  
  @Override
  public Class<?>[] getTestSuites() {
//    modulePath = moduleData.getAppPaths().getTestModuleURL() + moduleName + ".";    
    Class<?>[] testSuitesClasses = null;    
//    try {
//      testSuitesClasses = mapTestSuitesToClasses();      
//    } catch (ClassNotFoundException e) {
//      System.out.println("TestSuiteClass: getTestSuites -> class not found.");
//      System.out.println("Most likely the module xml file is wrong.");
//      System.out.println("Looking for test suites (classes) in: [" + modulePath + "]");
//    }
//    return testSuitesClasses;
    	return null;
  }
  
//  private Class<?>[] mapTestSuitesToClasses() throws ClassNotFoundException{
//    Class<?>[] testSuitesClasses = new Class<?>[testSuites.size()];
//    int idx = 0;
//    for (TestSuite ts : testSuites) {
//      Class<?> clazz = 
//          ts.getClazz(modulePath);
//      testSuitesClasses[idx++] = clazz;
//    }    
//    return testSuitesClasses;        
//  }
  
  
}