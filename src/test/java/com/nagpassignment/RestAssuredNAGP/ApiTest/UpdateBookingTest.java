package com.nagpassignment.RestAssuredNAGP.ApiTest;

import org.testng.annotations.Test;

import com.nagpassignment.RestAssuredNAGP.APIObject.BaseRestParameter;
import com.nagpassignment.RestAssuredNAGP.APIObject.CreateBookingAPIObject;
import com.nagpassignment.RestAssuredNAGP.APIObject.UpdateBookingAPIObject;
import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;
import com.nagpassignment.RestAssuredNAGP.Utils.AssertionUtil;
import com.nagpassignment.RestAssuredNAGP.Utils.JSONUtil;

import io.restassured.response.Response;

public class UpdateBookingTest extends BaseAPITest{
	private AssertionUtil assertionUtil = new AssertionUtil();
	CreateBookingAPIObject createBookingAPIObject = new CreateBookingAPIObject();
	UpdateBookingAPIObject updateBookingAPIObject = new UpdateBookingAPIObject();
	
	
	@Test()
	public void VerifyUpdateBookingAPIWithoutAuthorization() throws Exception {
		Response response =	 updateBookingAPIObject.updateBooking("1234","Deepak", "Sharma", Double.valueOf(111), true, "Lunch", "2018-01-01", "2019-01-01",restParameter);	
		assertionUtil.verifyResult(extentTest,response.getStatusCode() , 403, " Verify that the API returns 403");	
		
	}
	
	@Test()
	public void VerifyUpdateBookingAPIWithInvalidBookingID() throws Exception {
		
		reporterUtil.reportStep(extentTest, " Step - Call Update API with invalid booking id and verify the error");
		String token = updateBookingAPIObject.getAuthorisationToken(restParameter);
		restParameter.setHeader("Cookie","token="+token);
		Response response =	 updateBookingAPIObject.updateBooking("1234678","Deepak", "Sharma", Double.valueOf(111), true, "Lunch", "2018-01-01", "2019-01-01",restParameter);	
		assertionUtil.verifyResult(extentTest,response.getStatusCode() , 405, " Verify that the API returns 405");	
		
	}
	
	@Test()
	public void VerifyUpdateBookingAPIWithValidBookingID() throws Exception {
		
		reporterUtil.reportStep(extentTest, " Step - Create a booking and update First name and additionalneeds in  that booking");
		Response response =	 createBookingAPIObject.createBooking("Deepak", "Sharma", Double.valueOf(111), true, "Lunch", "2018-01-01", "2019-01-01",restParameter);
		String bookingId = JSONUtil.getNodeValue(response.asPrettyString(), "bookingid");
		String token = updateBookingAPIObject.getAuthorisationToken(restParameter);
		restParameter.setHeader("Cookie","token="+token);
		response =	 updateBookingAPIObject.updateBooking(bookingId,"Edit", "Sharma", Double.valueOf(111), true, "Breakfast", "2018-01-01", "2019-01-01",restParameter);	
		assertionUtil.verifyResult(extentTest,response.getStatusCode() , 200, " Verify that the API returns 200");	
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "firstname"),"Edit","Verify correct first name in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "lastname"),"Sharma","");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "additionalneeds"),"Breakfast","Verify correct additional needs in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "totalprice"),"111","Verify correct total price in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "depositpaid"),"true","Verify correct deposit paid in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "bookingdates.checkin"),"2018-01-01","Verify correct checkin date in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "bookingdates.checkout"),"2019-01-01","Verify correct checkout date in response");
		
	}
	
	@Test()
	public void VerifyUpdateBookingAPIWithPartialUpdate() throws Exception {
		reporterUtil.reportStep(extentTest, " Step - Create a booking and partially update First name in  that booking");
		restParameter.setHeader("Content-type","application/json");
		Response response =	 createBookingAPIObject.createBooking("Deepak", "Sharma", Double.valueOf(111), true, "Lunch", "2018-01-01", "2019-01-01",restParameter);
		String bookingId = JSONUtil.getNodeValue(response.asPrettyString(), "bookingid");
		String token = updateBookingAPIObject.getAuthorisationToken(restParameter);
		restParameter.setHeader("Cookie","token="+token);
		response =	 updateBookingAPIObject.partialUpdateBooking(bookingId,"Edit", "Sharma", null, null, null,null, null,restParameter);	
		assertionUtil.verifyResult(extentTest,response.getStatusCode() , 200, " Verify that the API returns 200");	
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "firstname"),"Edit","Verify correct first name in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "lastname"),"Sharma","");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "additionalneeds"),"Lunch","Verify correct additional needs in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "totalprice"),"111","Verify correct total price in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "depositpaid"),"true","Verify correct deposit paid in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "bookingdates.checkin"),"2018-01-01","Verify correct checkin date in response");
		assertionUtil.verifyResult(extentTest,JSONUtil.getNodeValue(response.asPrettyString(), "bookingdates.checkout"),"2019-01-01","Verify correct checkout date in response");
		
	}
	
	

}
