package com.nagpassignment.RestAssuredNAGP.ApiTest;



import com.nagpassignment.RestAssuredNAGP.APIObject.*;
import org.testng.annotations.Test;

import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;
import com.nagpassignment.RestAssuredNAGP.Utils.AssertionUtil;

import io.restassured.response.Response;

import static junit.framework.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


public class DeleteAuthorTest extends BaseAPITest {
    private AssertionUtil assertionUtil = new AssertionUtil();
    AuthorAPIObject authorsAPIObject = new AuthorAPIObject();
    @Test()
    public void deleteAuthorSuccessfully() throws Exception {
        Response response = authorsAPIObject.deleteAuthor(1, restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "Author deleted successfully");
    }

    @Test()
    public void deleteNonExistentAuthor() throws Exception {
        Response response = authorsAPIObject.deleteAuthor(999, restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 404, "Not Found: Author does not exist");
    }

    @Test()
    public void deleteAuthorWithInvalidId() throws Exception {
        Response response = authorsAPIObject.deleteAuthor(-1, restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 400, "Bad Request: Invalid author ID");
    }

    @Test()
    public void deleteAuthorTwice() throws Exception {
        // First deletion
        Response responseFirst = authorsAPIObject.deleteAuthor(1, restParameter);
        assertionUtil.verifyResult(extentTest, responseFirst.getStatusCode(), 200, "Author deleted successfully");

        // Second deletion attempt
        Response responseSecond = authorsAPIObject.deleteAuthor(restParameter);
        assertionUtil.verifyResult(extentTest, responseSecond.getStatusCode(), 405, "Not Found: Author does not exist");
    }


}
