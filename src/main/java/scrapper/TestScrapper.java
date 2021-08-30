package scrapper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestScrapper {

	public static List<WebElement> getListOfElements(WebDriver driver, String className) {
		List<WebElement> elements = driver.findElements(By.className(className));
		for (WebElement e : elements) {
			WebElement i = e.findElement(By.xpath(".//i"));
			System.out.println(i.getAttribute("class"));
		}
		return elements;
	}
	
	public static void clickElement() {
		
	}
}
