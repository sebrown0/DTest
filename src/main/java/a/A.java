package a;

import java.util.Optional;


public class A {

	public static void main(String[] args) {
		SiteMapContentGetter<Root> contentGetter = 
				new SiteMapContentGetter<Root>("C:/Users/Brown/eclipse-workspace/2021/DTest/src/main/java/a/Main.xml", Root.class);
		Optional<Root> root = contentGetter.getContent();
		System.out.println(root.get().getContent().getParent().get(0).getChild().getType());
//		System.out.println(root.get().getParent().get(1).getChild().getType());
	}

}
