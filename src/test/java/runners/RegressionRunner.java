package runners;

import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@Listeners(utils.ExtentManager.class)
@CucumberOptions(features="./src/test/resources/features",glue={"stepDefinitions","hooks"}, tags = "@Regression")
public class RegressionRunner extends AbstractTestNGCucumberTests{

}
