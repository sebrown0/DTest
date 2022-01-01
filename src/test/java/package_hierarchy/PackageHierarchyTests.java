package package_hierarchy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import site_mapper.jaxb.classes.pom.PackageHierarchy;
import utils.PackageMaker;

class PackageHierarchyTests {

	@Test
	void test_getHierarchyDotNotation() {
		PackageHierarchy ph = new PackageHierarchy("./src/main/java", "object_models");
		ph.addCurrent("payroll");
		ph.addCurrent("left");
		assertEquals("object_models.payroll.left", ph.getHierarchyDotNotation());
//		System.out.println();
	}
	@Test
	void test_getHierarchyFwdSlashNotation() {
		PackageHierarchy ph = new PackageHierarchy("./src/main/java", "object_models");
		ph.addCurrent("payroll");
		ph.addCurrent("left");
		assertEquals("object_models/payroll/left", ph.getHierarchyFwdSlashNotation());
	}

	@Test
	void test_packageMaker() {
		/*
		 * CHECK PROJECT EXPLORER MANUALLY TO CONFIRM TEST. 
		 * DELETE PACKAGES AFTER.
		 */
		PackageHierarchy ph = new PackageHierarchy("./src/main/java", "object_models");
		ph.addCurrent("payroll");
		PackageMaker.makeWithPackageInfo(ph);		
		PackageMaker.makeWithPackageInfo(ph.addCurrent("left"));
		PackageMaker.makeWithPackageInfo(ph.addCurrent("employees"));
	}
}
