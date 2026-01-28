package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.ActionUtils;
import utils.WaitUtils;

public class ProductsPage {
	private WebDriver driver;
	public ProductsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//*[@data-test='product-sort-container']")
	private WebElement dropDown;
	@FindBy(className="title")
	private WebElement header;
	@FindBy(xpath="//*[@data-test='shopping-cart-link']")
	private WebElement cart;
	@FindBy(xpath="//option[text()='Price (low to high)']")
	private WebElement sortByPrice;
	
	public boolean isPageLoaded() {
		if(header.getText().equals("Products")) {
			return true;
		}
		return false;
	}
	//public void checkCart() {
	//	cart.click();
	//}
	public void changeSort(String sortCriteria) {
		WaitUtils.waitForVisibility(driver, dropDown);
		Select s=new Select(dropDown);
		s.selectByContainsVisibleText(sortCriteria);
	}
	public void clickSortByPriceAscending() {
		ActionUtils.hover(driver,dropDown);
		ActionUtils.click(driver, dropDown);
		WaitUtils.waitForVisibility(driver, sortByPrice);
		ActionUtils.hover(driver,sortByPrice);
		ActionUtils.click(driver, sortByPrice);
		
		
	}
}

