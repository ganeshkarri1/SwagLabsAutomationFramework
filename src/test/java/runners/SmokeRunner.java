package runners;

import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@Listeners(utils.ExtentManager.class)
@CucumberOptions(features="./src/test/resources/features",glue={"stepDefinitions","hooks"}, tags = "@Smoke")
public class SmokeRunner extends AbstractTestNGCucumberTests {

}
