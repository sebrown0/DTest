/**
 * 
 */
package object_models.helpers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import object_models.navigation.left_side_menu.LeftMenuPayroll.MenuItem;

/**
 * @author Steve Brown
 *
 */
public class TitleChecker {
	private List<String> missing = new LinkedList<>();
	private List<String> additional = new ArrayList<>();
	private List<String> held = new ArrayList<>();	
	private Map<String, MenuItem> expected;
	private List<String> actual;
	
	public TitleChecker(Map<String, MenuItem> expected, List<String> actual) {
		this.expected = expected;
		this.actual = actual;
	}

	public TitleChecker mapMissing() {
		expected.entrySet().forEach(set -> {
			String key = set.getKey();
			missing.add(key);
			MenuItem menuItem = set.getValue();
			menuItem
				.getChildNames()
				.ifPresent(
						names -> {names.forEach(					
								n -> {missing.add(n);}); 
						});			
				});		
		return this;
	}
	
	public boolean isCorrect() {
		actual.forEach( n -> {
			if(isParent(n)) {
				System.out.println("Parent found: " + n);
				held.add(n);
			}else {
				if(childNotRemoved(n)) {
					additional.add(n);
					System.out.println("Actual does not exist in expected");									
				}
			}
		});
		removeHeldParents();
		return false;
	}

	private boolean isParent(String name) {
		return expected.containsKey(name) ? true : false; 
	}
	
	/*
	 * If the child is found remove it from the 'missing' list.
	 */
	private boolean childNotRemoved(String name) {
		boolean childRemoved = false;
		
		for (var entry : expected.entrySet()) {
			MenuItem item = entry.getValue();
			List<String> names;
			
			if(item.getChildNames().isPresent()) {
				names = item.getChildNames().get();
				for (var n : names) {
					if(n.equals(name)) {
//						System.out.println("Rempving child " + n + " from " + item.getParentName());
						missing.remove(n);
						childRemoved = true;
						break;
					}
				}			
			}			
		}
		return !childRemoved;
	}
	
	private void removeHeldParents() {
		held.forEach(h -> { 
			missing.remove(h);
		});
	}
	
	public void report() {
		System.out.println("-------- Missing -----------");
		missing.forEach(m -> System.out.println(m));
		System.out.println("-------- Additional -----------");
		additional.forEach(a -> System.out.println(a));
	}
}	






//	public void r(String p, String c) {
//		MenuItem  i = expected.get(p);
//		Optional<List<String>> names = i.getChildNames();
//		if(names.isPresent()) {
//			System.out.println("Looking for: " + c + " in: " + p);
//			if(names.get().contains(c)) {
//				System.out.println(p + " has " + c);
//				System.out.println("b4 = " + missing.size());
//				missing.remove(c);
//				System.out.println("af = " + missing.size());
////				missing.forEach(n -> System.out.println("----" + n));
//			}
//			
//		}
//	}
//	
//	private void removeFromList(Optional<String> prntName,	Optional<String> childName) {
//		prntName.ifPresent( 
//				p -> childName.ifPresent(
//						c -> 	{ System.out.println("Removing child: " + childName.get() + " from parent: " + prntName.get());
//										String prnt = prntName.get();
//										String chld = childName.get();
//										MenuItem i = expected.get(prnt);
//										Optional<List<String>> names = i.getChildNames();
////										names.ifPresent(n -> {System.out.println("-->>>>" + n.); }); //n.remove(chld);
//									} 
//		));		
//	}
		

	
	
	
	
	
	
	