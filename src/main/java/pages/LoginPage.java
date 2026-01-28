package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class LoginPage {
	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="user-name")
	private WebElement userName;
	@FindBy(id="password")
	private WebElement password;
	@FindBy(id="login-button")
	private WebElement submit;
	@FindBy(xpath="//*[@data-test='error']")
	private WebElement error;
	public boolean isPageLoaded() {
		if(driver.getTitle().equals("Swag Labs")) {
			return true;
		}
		return false;
	}
	public void login(String username,String pass) {
		WaitUtils.waitForVisibility(driver, userName);
		userName.sendKeys(username);
		password.sendKeys(pass);
		submit.click();
	}
	public String displayError() {
		WaitUtils.waitForVisibility(driver, error);
		return error.getText();
	}

}

