package com.nagpassignment.RestAssuredNAGP.Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.nagpassignment.RestAssuredNAGP.APIObject.BaseRestParameter;
import com.nagpassignment.RestAssuredNAGP.Utils.ReporterUtil;
import com.nagpassignment.RestAssuredNAGP.Utils.ServerPingUtility;

/**
 * This is the base class for API tests containing common setup and teardown methods.
 */
public class BaseAPITest {
    public static Logger logger = LogManager.getLogger(BaseAPITest.class);
    protected ExtentReports extentReports;
    protected static ExtentTest extentTest;
    protected BaseRestParameter restParameter;
    protected ReporterUtil reporterUtil;

    /**
     * Setup method to initialize Extent Reports and check server reachability before running tests.
     */
    @BeforeSuite
    public void setUp() {
        // Set up Extent Report configurations
        extentReports = new ExtentReports();
        
        // Set up reporter configurations
        reporterUtil = new ReporterUtil();
        
        // ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\deepaksharma15\\eclipse-workspace\\RestAssuredNAGP\\src\\main\\java\\com\\nagpassignment\\RestAssuredNAGP\\Reporting\\ExtentReport.html");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/ExtentReportResults.html");
        extentReports.attachReporter(htmlReporter);
        logger.info("Extent Report Initialized successfully");
    }

    /**
     * Setup method to configure before each test method and check server reachability.
     *
     * @param result The ITestResult of the test method.
     * @throws Exception 
     */
    @BeforeMethod
    public void setupBeforeMethod(ITestResult result) throws Exception {
        restParameter = new BaseRestParameter();
        restParameter.setHeader("Content-Type","application/json");
        extentTest = extentReports.createTest(result.getMethod().getMethodName(), result.getMethod().getMethodName());
        boolean isServerReachable = ServerPingUtility.isServerUp("https://restful-booker.herokuapp.com/booking");
        if (!isServerReachable) {
            throw new RuntimeException("Server is not reachable, skipping the test.");
        }
    }

    /**
     * Teardown method to flush Extent Reports after the test suite.
     */
    @AfterSuite
    public void afterSuite() {
        System.out.println("FlushingReport");
        extentReports.flush();

    }
}
