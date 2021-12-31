package package_hierarchy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import site_mapper.jaxb.classes.pom.PackageHierarchy;

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
//		System.out.println();
	}

}
