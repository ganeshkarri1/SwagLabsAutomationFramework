package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.DriverFactory;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentManager implements ITestListener {

    private static ExtentReports extent = new ExtentReports();
    private static ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
    
    // ThreadLocal ensures reports are thread-safe during parallel execution
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	public static ThreadLocal<String> screenshotPath = new ThreadLocal<>();
    @Override
    public void onStart(ITestContext context) {
        extent.attachReporter(spark);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Ganesh");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new entry in the report for the current test method
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	//test.get().log(Status.FAIL, ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), result.getMethod().getMethodName()));
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
       // String path=ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), result.getMethod().getMethodName());
        try{test.get().addScreenCaptureFromPath(screenshotPath.toString());}
        catch(Exception e) {
        	test.get().log(Status.FAIL, "Failed to attach screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        // CRITICAL: This writes everything to the file
        extent.flush();
    }
    
}

