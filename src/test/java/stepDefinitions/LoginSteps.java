package stepDefinitions;

import org.testng.Assert;

import Base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginSteps {
	LoginPage page;
	ProductsPage productPage;
	
	@Given("user gives the username as {string} and password as {string}")
	public void user_gives_the_username_as_and_password_as(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
		page=new LoginPage(DriverFactory.getDriver());
		page.login(string, string2);
	}
	@Then("the products page should appear")
	public void the_products_page_should_appear() {
	    // Write code here that turns the phrase above into concrete actions
		productPage=new ProductsPage(DriverFactory.getDriver());
		Assert.assertEquals(true, productPage.isPageLoaded());
	}
	@Then("then error message should be diplayed")
	public void then_error_message_should_be_diplayed() { 
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", page.displayError());
		}
	}



