package com.nagpassignment.RestAssuredNAGP.Utils;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;


public class ReporterUtil extends BaseAPITest{
	

 public static void reportStep(ExtentTest extentTest,String message) {
	 
	 extentTest.info(MarkupHelper.createLabel(message, ExtentColor.BLACK));
	 logger.info(message);
	 
	 
 }
 
public static void reportPass(ExtentTest extentTest,String message) {
	 
	 extentTest.info(MarkupHelper.createLabel(message, ExtentColor.GREEN));
	 logger.info(message);
		 
 }


public static void reportFail(ExtentTest extentTest,String message) {
	 
	 extentTest.info(MarkupHelper.createLabel(message, ExtentColor.RED));
	 logger.info(message);
		 
}
	
	
}
