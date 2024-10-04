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


public class PostAuthorTest extends BaseAPITest {
    private AssertionUtil assertionUtil = new AssertionUtil();
    AuthorAPIObject authorsAPIObject = new AuthorAPIObject();
    CreateBookingAPIObject createBookingAPIObject = new CreateBookingAPIObject();
    @Test()
    public void addAuthorSuccessfully() throws Exception {
        Response response = authorsAPIObject.addAuthor(1, 101, "John", "Doe", restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "Author created successfully");
        restParameter.clear();
    }

    @Test()
    public void addAuthorWithMissingFields() throws Exception {
        Response response = authorsAPIObject.addAuthor(2, 0, "Jane", null, restParameter); // Missing lastName
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 400, "Bad Request: Missing required fields");
        restParameter.clear();
    }

    @Test()
    public void addAuthorWithInvalidId() throws Exception {
        Response response = authorsAPIObject.addAuthor(-1, 102, "Invalid", "Author", restParameter); // Invalid ID
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 400, "Bad Request: Invalid author ID");
        restParameter.clear();
    }

    @Test()
    public void addAuthorWithMaxLengthFields() throws Exception {
        String maxLengthName = "A" + new String(new char[254]).replace("\0", "A"); // Max length for firstName
        String maxLengthLastName = "B" + new String(new char[254]).replace("\0", "B"); // Max length for lastName
        Response response = authorsAPIObject.addAuthor(3, 103, maxLengthName, maxLengthLastName, restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "Author created successfully with max length fields");
        restParameter.clear();
    }

    @Test()
    public void addDuplicateAuthor() throws Exception {
        Response response = authorsAPIObject.addAuthor(1, 104, "John", "Doe", restParameter); // Duplicate ID
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 409, "Conflict: Author with this ID already exists");
        restParameter.clear();
    }




}
