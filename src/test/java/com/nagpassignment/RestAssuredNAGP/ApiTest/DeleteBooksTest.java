package com.nagpassignment.RestAssuredNAGP.ApiTest;

import com.nagpassignment.RestAssuredNAGP.APIObject.*;
import org.testng.annotations.Test;

import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;
import com.nagpassignment.RestAssuredNAGP.Utils.AssertionUtil;

import io.restassured.response.Response;

import static junit.framework.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


public class DeleteBooksTest extends BaseAPITest {
    private AssertionUtil assertionUtil = new AssertionUtil();
    BooksAPIObject booksAPIObject = new BooksAPIObject();

    @Test()
    public void deleteBookSuccessfully() throws Exception {
        Response response = booksAPIObject.deleteBook(1, restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "Book deleted successfully");
    }

    @Test()
    public void deleteNonExistentBook() throws Exception {
        Response response = booksAPIObject.deleteBook(999, restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 404, "Not Found: Book does not exist");
    }

    @Test()
    public void deleteBookWithInvalidId() throws Exception {
        Response response = booksAPIObject.deleteBook(-1, restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 400, "Bad Request: Invalid book ID");
    }

    @Test()
    public void deleteBookTwice() throws Exception {
        // First deletion
        Response responseFirst = booksAPIObject.deleteBook(1, restParameter);
        assertionUtil.verifyResult(extentTest, responseFirst.getStatusCode(), 200, "Book deleted successfully");

        // Second deletion attempt
        Response responseSecond = booksAPIObject.deleteBook(restParameter); // Attempt to delete the same book again
        assertionUtil.verifyResult(extentTest, responseSecond.getStatusCode(), 405, "Not Found: Book does not exist");
    }

}
