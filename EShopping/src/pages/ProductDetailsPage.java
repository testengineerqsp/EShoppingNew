package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import genericlibs.WebActionUtil;

public class ProductDetailsPage extends BasePage {
	
	@FindBy(xpath="//i[@class='icon-minus']")
	private WebElement minusIcon;
	
	@FindBy(xpath="//i[@class='icon-plus']")
	private WebElement plusIcon;
	
	@FindAll({@FindBy(id="group_1"), @FindBy(name="group_1")})
	private WebElement sizeListBox;
		
	@FindBy(xpath="//ul[@id='color_to_pick_list']//a")
	private List<WebElement> colorPickerList;
	
	@FindBy(name="Submit")
	private WebElement addToKartButton;
	
	@FindBy(linkText="Proceed to checkout")
	private WebElement proceedToCheckoutButton;
	
	@FindBy(xpath="//span[@title='Continue shopping']")
	private WebElement continueToShoppingButton;
	
	public WebElement getMinusIcon() {
		return minusIcon;
	}

	public WebElement getPlusIcon() {
		return plusIcon;
	}

	public WebElement getSizeListBox() {
		return sizeListBox;
	}

	public List<WebElement> getColorPickerList() {
		return colorPickerList;
	}

	public WebElement getAddToKartButton() {
		return addToKartButton;
	}

	public WebElement getProceedToCheckoutButton() {
		return proceedToCheckoutButton;
	}

	public WebElement getContinueToShoppingButton() {
		return continueToShoppingButton;
	}

	public ProductDetailsPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public void increaseOrDecreaseQuantity(int count, boolean increase) {
		WebElement ele;
		if(increase) {
			ele = plusIcon;
		} else {
			ele = minusIcon;
		}
		
		for(int i=1;i<=count;i++) {
			webActionUtil.elementClick(ele);
		}
	}
	
	public void selectSize(String size) {
		webActionUtil.selectByVisibleTextOrIndex(sizeListBox, size);
	}
	
	public void selectColor(String colorName) {
		for(WebElement ele:colorPickerList) {
			if(ele.getAttribute("name").equalsIgnoreCase(colorName)) {
				webActionUtil.elementClick(ele);
				break;
			}
		}
	}
	
	public void clickOnAddToKart() {
		webActionUtil.elementClick(addToKartButton);
	}
	
	public OrderDetailsPage clickOnProceedToCheckOutButton() {
		webActionUtil.elementClick(proceedToCheckoutButton);
		return new OrderDetailsPage(driver, webActionUtil);
	}
	
	public void clickOnContinueToCheckOutButton() {
		webActionUtil.elementClick(proceedToCheckoutButton);
	}
	
	public OrderDetailsPage addItemToKart(int count, 
							  boolean increase, 
							  String size,
							  String colorName) {
		increaseOrDecreaseQuantity(count, increase);
		selectSize(size);
		selectColor(colorName);
		clickOnAddToKart();
		return clickOnProceedToCheckOutButton();
	}
}






