package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import genericlibs.WebActionUtil;

public class HomePage extends BasePage {
	
	@FindBy(xpath="//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a")
	private List<WebElement> allMenuLinksList;
	
	@FindBy(linkText="Sign out")
	private WebElement signOutLink;
	
	public WebElement getSignOutLink() {
		return signOutLink;
	}

	public List<WebElement> getAllMenuLinksList() {
		return allMenuLinksList;
	}

	public HomePage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public BasePage clickOnMenu(String menuName) {
		for(WebElement ele:allMenuLinksList) {
			if(ele.getText().equalsIgnoreCase(menuName)) {
				webActionUtil.elementClick(ele);
				break;
			}
		}
		
		switch(menuName.toLowerCase()) {
			case "dresses": return new DressesPage(driver, webActionUtil); 
			case "t-shirts": return new TShirtsPage(driver, webActionUtil); 
			case "women": return new WomenPage(driver, webActionUtil); 
			default: return null; 
		}
	}
	
	public void signOut() {
		webActionUtil.elementClick(signOutLink);
	}
}
