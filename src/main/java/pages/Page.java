package pages;

import org.openqa.selenium.WebDriver;

public class Page {
	public static void get(String uri, WebDriver driver) {
	    driver.get(uri);
	}
}
