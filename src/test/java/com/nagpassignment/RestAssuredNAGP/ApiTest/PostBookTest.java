package com.nagpassignment.RestAssuredNAGP.ApiTest;



import com.nagpassignment.RestAssuredNAGP.APIObject.*;
import org.testng.annotations.Test;

import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;
import com.nagpassignment.RestAssuredNAGP.Utils.AssertionUtil;

import io.restassured.response.Response;

import static junit.framework.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


public class PostBookTest extends BaseAPITest {
    private AssertionUtil assertionUtil = new AssertionUtil();
    BooksAPIObject booksAPIObject = new BooksAPIObject();


    @Test()
    public void addBookSuccessfully() throws Exception {
        Response response = booksAPIObject.addBook(1, "The Great Gatsby", "A classic novel.", 180, "An excerpt here.", "2024-10-03T14:20:09.873Z", restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 201, "Book created successfully");
        restParameter.clear();
    }

    @Test()
    public void addBookWithMissingTitle() throws Exception {
        Response response = booksAPIObject.addBook(2, null, "Description", 250, "Excerpt", "2024-10-03T14:20:09.873Z",restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 400, "Bad Request: Missing title");
        restParameter.clear();
    }

    @Test()
    public void addBookWithInvalidPageCount() throws Exception {
        Response response = booksAPIObject.addBook(3, "Valid Title", "Description", -5, "Excerpt", "2024-10-03T14:20:09.873Z",restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 400, "Bad Request: Invalid page count");
        restParameter.clear();
    }

    @Test()
    public void addBookWithMaxLengthFields() throws Exception {
        StringBuilder maxLengthTitle = new StringBuilder();
        StringBuilder maxLengthDescription = new StringBuilder();

        for (int i = 0; i < 255; i++) {
            maxLengthTitle.append("A");
            maxLengthDescription.append("B");
        }

        Response response = booksAPIObject.addBook(4, maxLengthTitle.toString(), maxLengthDescription.toString(), 300, "Excerpt", "2024-10-03T14:20:09.873Z", restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 201, "Book created successfully with max length fields");
        restParameter.clear();
    }


    @Test()
    public void addDuplicateBook() throws Exception {
        Response response = booksAPIObject.addBook(1, "Duplicate Title", "Description", 180, "Excerpt", "2024-10-03T14:20:09.873Z",restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 409, "Conflict: Book with this ID already exists");
        restParameter.clear();
    }


}
