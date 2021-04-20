package genericlibs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//Wrapper class to Perform Multiple WebActions Together
public class WebActionUtil {
	//Global Objects which can be re-used in all the Wrapper Methods
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jse;
	Actions actions;
	
	public WebActionUtil(WebDriver driver, long explict) {
		//Initializing All Global Objects 
		this.driver=driver;
		jse=(JavascriptExecutor) driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, explict);
	}
	
	public void enterData(WebElement target,String text) {
		target.clear();
		target.sendKeys(text);
	}
	
	public void elementClick(WebElement target) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(target));
			target.click();
		}	catch (Exception e) {
			jsClick(target);
		}		
	}
	
	public void jsClick(WebElement target) {
		jse.executeScript("arguments[0].click();", target);
	}
	
	public void scrollTopOrDown(boolean top) {
		if(top) {
			jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		} else {
			jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		}		
	}
	
	public void jsEnterData(WebElement target, String text) {
		jse.executeScript("arguments[0].value='';", target);
		jse.executeScript("arguments[0].value=arguments[1];", target, text);
	}
	
	public void moveToElement(WebElement target) {
		actions.moveToElement(target).perform();
	}
	
	public void doubleClick(WebElement target) {
		actions.doubleClick(target).perform();
	}
	
	public void dragAndDrop(WebElement source, WebElement target) {
		actions.dragAndDrop(source, target).perform();
	}
	
	public void switchToFrame(String indexNameOrId) {
		try {
			int index=Integer.parseInt(indexNameOrId);
			driver.switchTo().frame(index);
		} catch(NumberFormatException e) {
			driver.switchTo().frame(indexNameOrId);
		}
	}
	
	public void swithToWindow(String targetWindowtitle) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for(String wid:allWindowIds) {
			driver.switchTo().window(wid);
			if(driver.getTitle().equalsIgnoreCase(targetWindowtitle)) {
				break;
			}
		}
	}
	
	public void swithToParentWindow(String targetWindowtitle) {
		List<String> allWindowIds = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allWindowIds.get(0));	
	}
	
	public void closeAllChildWindows(String targetWindowtitle) {
		String parentWindowId = driver.getWindowHandle();
		Set<String> allWindowIds = driver.getWindowHandles();
		allWindowIds.remove(parentWindowId);
		for(String wid:allWindowIds) {
			driver.switchTo().window(wid);
			driver.close();
		}
		driver.switchTo().window(parentWindowId);
	}
	
	public void verifyAlertTextAndAccept(String alertTextContains) {
		wait.until(ExpectedConditions.alertIsPresent());
		if(driver.switchTo().alert().getText().contains(alertTextContains)) {
			driver.switchTo().alert().accept();
		}
	}
	
	public void selectByVisibleTextOrIndex(WebElement targetListBox, String visibleTextOrIndex) {
		Select select = new Select(targetListBox);
		try {
			int index=Integer.parseInt(visibleTextOrIndex);
			select.selectByIndex(index);
		} catch(NumberFormatException e) {
			select.selectByVisibleText(visibleTextOrIndex);
		}
	}
	
	public void waitForInvibilityOfElement(WebElement target) {
		wait.until(ExpectedConditions.invisibilityOf(target));
	}
}
