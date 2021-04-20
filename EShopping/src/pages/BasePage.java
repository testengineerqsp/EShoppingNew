package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import genericlibs.WebActionUtil;

public class BasePage {
	
	WebDriver driver;
	WebActionUtil webActionUtil;
	
	public BasePage(WebDriver driver, WebActionUtil webActionUtil) {
		this.driver=driver;
		this.webActionUtil=webActionUtil;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyTitle(String expectedTitle) {
		return driver.getTitle().equalsIgnoreCase(expectedTitle);
	}
	
	public boolean verifyCurrentUrl(String expectedUrl) {
		return driver.getCurrentUrl().equalsIgnoreCase(expectedUrl);
	}
}
