package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElementUtil {

	public static List<WebElement> getElementsThatAreChildrenOfElement(WebElement we){
		List<WebElement> elements = we.findElements(By.xpath("*"));
		return elements;
	}
}
