package com.nagpassignment.RestAssuredNAGP.ApiTest;

import org.testng.annotations.Test;

import com.nagpassignment.RestAssuredNAGP.APIObject.CreateBookingAPIObject;
import com.nagpassignment.RestAssuredNAGP.APIObject.DeleteBookingAPIObject;
import com.nagpassignment.RestAssuredNAGP.APIObject.AuthorAPIObject;
import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;
import com.nagpassignment.RestAssuredNAGP.Utils.AssertionUtil;
import com.nagpassignment.RestAssuredNAGP.Utils.JSONUtil;

import io.restassured.response.Response;

public class DeleteBookingTest  extends BaseAPITest {
	private AssertionUtil assertionUtil = new AssertionUtil();
	CreateBookingAPIObject createBookingAPIObject = new CreateBookingAPIObject();
	DeleteBookingAPIObject deleteBookingAPIObject = new DeleteBookingAPIObject();
	AuthorAPIObject getBookingAPIObject = new AuthorAPIObject();
	
	@Test()
	public void VerifyDeleteBookingAPIWithoutAuthorization() throws Exception {
		restParameter.clear();
		reporterUtil.reportStep(extentTest, " Step - Call a delete booking API without doing authorization And verify the error");
		Response response =	 deleteBookingAPIObject.deleteBooking("1234",restParameter);	
		assertionUtil.verifyResult(extentTest,response.getStatusCode() , 403, " Verify that the API returns 403");
		
	}
	
	@Test()
	public void VerifyDeleteBookingAPIWithInvalidBookingID() throws Exception {
		
		reporterUtil.reportStep(extentTest, " Step - Call a delete booking API with invalid Booking ID");
		String token = deleteBookingAPIObject.getAuthorisationToken(restParameter);
		restParameter.setHeader("Cookie","token="+token);
		Response response =	 deleteBookingAPIObject.deleteBooking("1234567",restParameter);
		assertionUtil.verifyResult(extentTest,response.getStatusCode() , 404, " Verify that the API returns 405");
		
	}
	
	@Test()
	public void VerifyDeleteBookingAPIWithValidBookingID() throws Exception {
		reporterUtil.reportStep(extentTest, " Step - Create a booking and call Delete booking API to delete that booking");
		Response response =	 createBookingAPIObject.createBooking("Deepak", "Sharma", Double.valueOf(111), true, "Lunch", "2018-01-01", "2019-01-01",restParameter);
		String bookingId = JSONUtil.getNodeValue(response.asPrettyString(), "bookingid");
		String token = deleteBookingAPIObject.getAuthorisationToken(restParameter);
		restParameter.setHeader("Cookie","token="+token);
		response =	 deleteBookingAPIObject.deleteBooking(bookingId,restParameter);
		assertionUtil.verifyResult(extentTest,response.getStatusCode() , 201, " Verify that the API returns 201");	
		response = getBookingAPIObject.getBookingById(bookingId, restParameter);
		assertionUtil.verifyResult(extentTest,response.getStatusCode() , 404, " Verify that the API returns 404");
		
	}
	
	
}
