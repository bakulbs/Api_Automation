package com.nagpassignment.RestAssuredNAGP.ApiTest;

import java.util.List;

import com.nagpassignment.RestAssuredNAGP.APIObject.*;
import org.testng.annotations.Test;

import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;
import com.nagpassignment.RestAssuredNAGP.Utils.AssertionUtil;

import io.restassured.response.Response;

public class GetAuthorTest extends BaseAPITest{
	private AssertionUtil assertionUtil = new AssertionUtil();
	AuthorAPIObject getBookingAPIObject = new AuthorAPIObject();

		
	
	@Test()
	public void VerifyErrorWithInvalidBookingId() throws Exception {
		reporterUtil.reportStep(extentTest, " Step - Create booking with invalid booking ID and verify the error");
		Response response = getBookingAPIObject.getBookingById("6000",restParameter);
		assertionUtil.verifyResult(extentTest,response.getStatusCode() , 404 , "Verify error is returned for invalid booking ID");
		restParameter.clear();
	}

	@Test()
	public void verifyValidBookingId() throws Exception {
		reporterUtil.reportStep(extentTest, "Step - Attempt to retrieve booking with a valid booking ID and verify the response");
		Response response = getBookingAPIObject.getBookingById("12", restParameter);
		assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "Verify that booking is retrieved successfully");
		restParameter.clear();
	}

	@Test
	public void retrieveBookingWithoutAuthentication() throws Exception {
		// Step 1: Report the step being executed
		reporterUtil.reportStep(extentTest, "Step - Attempt to retrieve booking without authentication and verify the error");

		// Step 2: Define a booking ID to retrieve (make sure this ID is valid for your tests)
		String bookingId = "1";

		// Step 3: Call the API to retrieve the booking details without authentication
		Response response = getBookingAPIObject.getBookingByIdWithoutAuth(bookingId, restParameter);

		// Step 4: Verify that the status code is 401 Unauthorized
		assertionUtil.verifyResult(extentTest, response.getStatusCode(), 401, "Verify that an error is returned for unauthorized access");
		restParameter.clear();
	}
	@Test
	public void retrieveEmptyListOfBookings() throws Exception {
		reporterUtil.reportStep(extentTest, "Step - Retrieve a list of bookings when no bookings exist and verify the response");
		Response response = getBookingAPIObject.getBookingByBookId("300", restParameter);
		assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "Verify that the response status is 200 OK");
		assertionUtil.verifyResult(extentTest,response.asPrettyString().trim(),"[ ]","Verifying response should be Empty");
	}

	@Test()
	public void retrieveBooksWithPagination() throws Exception {
	restParameter.setQuery("page",1,"limit",10);
		List<String> allTitles= getBookingAPIObject.getAllBookings(restParameter);
		assertionUtil.verifyResult(extentTest,allTitles.size() ,10, "Verify that the response status has 10 records");
	}









}
