package com.nagpassignment.RestAssuredNAGP.ApiTest;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nagpassignment.RestAssuredNAGP.APIObject.BaseRestParameter;
import com.nagpassignment.RestAssuredNAGP.APIObject.CreateBookingAPIObject;
import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;
import com.nagpassignment.RestAssuredNAGP.Utils.AssertionUtil;
import com.nagpassignment.RestAssuredNAGP.Utils.JSONUtil;

import io.restassured.response.Response;
import net.bytebuddy.implementation.bind.annotation.Super;

public class CreateBookingTest extends BaseAPITest {
	private AssertionUtil assertionUtil = new AssertionUtil();
	CreateBookingAPIObject createBookingAPIObject = new CreateBookingAPIObject();

	@Test()
	public void VerifyBookingCreatedSuccessfullyWithValidData() throws Exception {
		
		reporterUtil.reportStep(extentTest, " Step - Create a booking with all valid data and verify booking is created successfully");
		Response response =	 createBookingAPIObject.createBooking("Deepak", "Sharma", Double.valueOf(111), true, "Lunch", "2018-01-01", "2019-01-01",restParameter);
		assertionUtil.verifyResult(extentTest,response.getStatusCode() , 200, "Verify Booking created sucessfully");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "booking.firstname"),"Deepak","Verify correct first name in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "booking.lastname"),"Sharma","Verify correct last name in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "booking.totalprice"),"111","Verify correct total price in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "booking.depositpaid"),"true","Verify correct deposit paid in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "booking.bookingdates.checkin"),"2018-01-01","Verify correct checkin date in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "booking.bookingdates.checkout"),"2019-01-01","Verify correct checkout date in response");

	}


	@Test()
	public void VerifyErrorWhenCheckinDateIsNotProvided() throws Exception {
		
		reporterUtil.reportStep(extentTest, " Step - Create a booking without Date and verify error");
		Response response =	 createBookingAPIObject.createBooking("Deepak", "Sharma", Double.valueOf(111), true, "Lunch", null, "2019-01-01",restParameter);
		assertionUtil.verifyResult(extentTest,response.getStatusCode() , 500 , "Verify Booking not created sucessfully");	
	}


	@Test()
	public void VerifyErrorWhenPriceIsNotProvided() throws Exception {
		
		reporterUtil.reportStep(extentTest, " Step - Create a booking without Price and verify error");
		Response response =	 createBookingAPIObject.createBooking(null, "Sharma", Double.valueOf(111), true, "Lunch", "2019-01-01", "2019-01-01",restParameter);
		assertionUtil.verifyResult(extentTest,response.getStatusCode() , 500 , "Verify Booking not created sucessfully");

	}




}
