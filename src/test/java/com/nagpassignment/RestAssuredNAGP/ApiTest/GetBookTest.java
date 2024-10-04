package com.nagpassignment.RestAssuredNAGP.ApiTest;


import java.util.List;
import java.util.Map;

import com.nagpassignment.RestAssuredNAGP.APIObject.*;
import junit.framework.Assert;
import org.testng.annotations.Test;

import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;
import com.nagpassignment.RestAssuredNAGP.Utils.AssertionUtil;

import io.restassured.response.Response;

import static junit.framework.Assert.assertTrue;
import static org.asynchttpclient.util.Assertions.assertNotNull;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public class GetBookTest extends BaseAPITest {
    private AssertionUtil assertionUtil = new AssertionUtil();
    BooksAPIObject getBookingAPIObject = new BooksAPIObject();
    CreateBookingAPIObject createBookingAPIObject = new CreateBookingAPIObject();
@Test()
    public void VerifyErrorWhenBookDoesNotExist() throws Exception{
    reporterUtil.reportStep(extentTest,"Create a book entry with invalid id and validate the error");
    Response response =getBookingAPIObject.getBooksById("1003",restParameter);
    assertionUtil.verifyResult(extentTest,response.getStatusCode(),404,"Verify a valid error message for invaid boking ID");
    restParameter.clear();

}
    @Test
    public void retrieveAllBooksWithCorrectData() throws Exception {
        reporterUtil.reportStep(extentTest, "Create a book entry with valid id and validate");
        Response response = getBookingAPIObject.getBooksById("1", restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "Verify all books returned");
        restParameter.clear();
    }
    @Test()
    public void retrieveAllBooksWithPagination() throws Exception {
    restParameter.setQuery("page",1,"limit",20);
    List<String> allTypes=BooksAPIObject.getAllBooks(restParameter);
    assertionUtil.verifyResult(extentTest,allTypes.size(),20,"Verify all books returned with pagination limit 20");
    }
    @Test()
    public void retrieveBooksWithoutAuthentication() throws Exception {
    reporterUtil.reportStep(extentTest, "Create a book entry with invalid id and validate");
    String bookingId ="12";
            Response response= getBookingAPIObject.getBooksById("12",restParameter);
    assertionUtil.verifyResult(extentTest,response.getStatusCode(),401,"Verify a valid booking ID");
    restParameter.clear();

    }
    @Test
    public void retrieveEmptyListOfBookings() throws Exception {
        reporterUtil.reportStep(extentTest, "Step - Retrieve a list of bookings when no bookings exist and verify the response");
        Response response = getBookingAPIObject.getBooksByBookId("100", restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "Verify that the response status is 200 OK");
        assertionUtil.verifyResult(extentTest,response.asPrettyString().trim(),"[ ]","Verifying response should be Empty");
    }

        }



   /* @Test()
    public void VerifyErrorWithInvalidBookingId() throws Exception {
        reporterUtil.reportStep(extentTest, " Step - Create book with invalid book ID and verify the error");
        Response response = getBookingAPIObject.getBookingById("109", restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 404, "Verify error is returned for invalid booking ID");
        restParameter.clear();
    }

    */



