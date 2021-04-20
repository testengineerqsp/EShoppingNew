package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import genericlibs.WebActionUtil;

public class OrderDetailsPage extends BasePage {
	
	String deleteIconXp="//td[@class='cart_product']/a[contains(@href,'prodcutId')]/../..//i[@class='icon-trash']";
	
	@FindBy(xpath="//td[@class='cart_product']/a")
	private List<WebElement> allProductsList;
	
	public List<WebElement> getAllProductsList() {
		return allProductsList;
	}

	public OrderDetailsPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public boolean isProductDisplayed(String productId) {
		productId = "id_product="+productId;
		for(WebElement product:allProductsList) {
			if(product.getAttribute("href").contains(productId)) {
				return true;
			}
		}
		return false;
	}

	public void deleteProduct(String productId) {
		productId = "id_product="+productId;
		deleteIconXp=deleteIconXp.replace("prodcutId", productId);
		WebElement prodcutTrash=driver.findElement(By.xpath(deleteIconXp));
		webActionUtil.elementClick(prodcutTrash);
		webActionUtil.waitForInvibilityOfElement(prodcutTrash);
	}
}
