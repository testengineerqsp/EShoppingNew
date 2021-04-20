package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import genericlibs.WebActionUtil;

public class DressesPage extends BasePage {
	
	@FindBy(xpath="//a[@class='product_img_link']")
	private List<WebElement> allProductsList;
	
	public List<WebElement> getAllProductsList() {
		return allProductsList;
	}

	public DressesPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public ProductDetailsPage clickOnProduct(String productId) {
		productId = "id_product="+productId;
		for(WebElement product:allProductsList) {
			if(product.getAttribute("href").contains(productId)) {
				webActionUtil.jsClick(product);
				break;
			}
		}
		return new ProductDetailsPage(driver, webActionUtil);
	}
}
