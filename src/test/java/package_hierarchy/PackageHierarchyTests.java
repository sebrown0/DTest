package package_hierarchy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import site_mapper.creators.PackageMaker;
import site_mapper.jaxb.classes.pom.PackageHierarchy;
import site_mapper.jaxb.classes.pom.SiteMap;

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
	void test_hierarchyReset() {
		PackageHierarchy ph = new PackageHierarchy("./src/main/java", "object_models");
		ph.addCurrent("payroll");
		ph.addCurrent("left");
		ph.reset();
		assertEquals("object_models", ph.getHierarchyFwdSlashNotation());
	}
	@Test
	void test_hierarchyRemoveCurrent() {
		PackageHierarchy ph = new PackageHierarchy("./src/main/java", "object_models");
		ph.addCurrent("payroll");
		ph.addCurrent("left");
		ph.removeCurrent();
		assertEquals("object_models/payroll", ph.getHierarchyFwdSlashNotation());
	}
	
	@Test
	void test_packageMaker() {
		/*
		 * CHECK PROJECT EXPLORER MANUALLY TO CONFIRM TEST. 
		 * DELETE PACKAGES AFTER.
		 */
		SiteMap siteMap = new SiteMap().setAuthor("SEB").setVersion("1.0.0");
		PackageHierarchy ph = new PackageHierarchy("./src/main/java", "object_models");
		ph.addCurrent("payroll");
		PackageMaker.makeWithPackageInfo(siteMap, ph);		
		PackageMaker.makeWithPackageInfo(siteMap, ph.addCurrent("left"));
		PackageMaker.makeWithPackageInfo(siteMap, ph.addCurrent("employees"));
	}
	
}
