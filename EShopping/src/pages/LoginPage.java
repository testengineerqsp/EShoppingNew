package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import genericlibs.WebActionUtil;

public class LoginPage extends BasePage {
	
	@FindBy(linkText="Sign in")
	private WebElement signInLink;
	
	@FindBy(id="email")
	private WebElement emailTextField;
	
	@FindBy(id="passwd")
	private WebElement passwordTextField;
	
	@FindBy(id="SubmitLogin")
	private WebElement signInButton;
	
	public WebElement getSignInLink() {
		return signInLink;
	}

	public WebElement getEmailTextField() {
		return emailTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getSignInButton() {
		return signInButton;
	}

	public LoginPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public HomePage signIn(String email, String password) {
		webActionUtil.elementClick(signInLink);
		webActionUtil.enterData(emailTextField, email);
		webActionUtil.enterData(passwordTextField, password);
		webActionUtil.elementClick(signInButton);
		return new HomePage(driver, webActionUtil);
	}
}


