package com.nagpassignment.RestAssuredNAGP.Utils;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;



public class AssertionUtil  extends BaseAPITest{
	

	public  void verifyResult(ExtentTest extentTest,  String actualResult, String expectedResult, String message) {
        extentTest.info("Performing assertion: " + message);
        extentTest.info("Actual Result: " + actualResult);
        extentTest.info("Expected Result: " + expectedResult);

        try {
            Assert.assertEquals(actualResult, expectedResult, message);
            ReporterUtil.reportPass(extentTest, "Assertion Passed - "+message);
           
        } catch (AssertionError e) {
        	ReporterUtil.reportFail(extentTest, "Assertion Failed - "+ message);
            extentTest.fail(e);
            
        }
    }
	
	public  void verifyResult(ExtentTest extentTest,  int actualResult, int expectedResult, String message) {
        extentTest.info("Performing assertion: " + message);
        extentTest.info("Actual Result: " + actualResult);
        extentTest.info("Expected Result: " + expectedResult);

        try {
            Assert.assertEquals(actualResult, expectedResult, message);
            ReporterUtil.reportPass(extentTest, "Assertion Passed - "+message);
           
        } catch (AssertionError e) {
        	ReporterUtil.reportFail(extentTest, "Assertion Failed - "+ message);
            extentTest.fail(e);
            
        }
    }
	
	public  void verifyTrue(ExtentTest extentTest, boolean actualResult,  String message) {
        extentTest.info("Performing assertion: " + message);
     
        try {
        	 Assert.assertTrue(actualResult, message);
        	 ReporterUtil.reportPass(extentTest, "Assertion Passed - "+message);
        } catch (AssertionError e) {
        	ReporterUtil.reportFail(extentTest, "Assertion Failed - "+ message);
            extentTest.fail(e);
           
        }
    }


}
