package com.nagpassignment.RestAssuredNAGP.ApiTest;
import com.nagpassignment.RestAssuredNAGP.APIObject.*;
import org.testng.annotations.Test;

import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;
import com.nagpassignment.RestAssuredNAGP.Utils.AssertionUtil;

import io.restassured.response.Response;

import static junit.framework.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


public class PutAuthorTest extends BaseAPITest {
    private AssertionUtil assertionUtil = new AssertionUtil();
    AuthorAPIObject authorAPIObject = new AuthorAPIObject();

    @Test()
    public void updateAuthorSuccessfully() throws Exception {
        Response response = authorAPIObject.updateAuthor(1, 23,"Updated FirstName", "Updated LastName", restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "Author updated successfully");
    }

    @Test()
    public void updateAuthorWithMaxLengthFields() throws Exception {
        String maxLengthFirstName = "A" + new String(new char[254]).replace("\0", "A"); // 255 characters for first name
        String maxLengthLastName = "B" + new String(new char[254]).replace("\0", "B"); // 255 characters for last name
        Response response = authorAPIObject.updateAuthor(1, 6,maxLengthFirstName, maxLengthLastName, restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "Author updated with max length fields");
    }


    @Test()
    public void updateAuthorWithInvalidId() throws Exception {
        Response response = authorAPIObject.updateAuthor(-1,0 ,"Invalid FirstName", "Invalid LastName", restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 400, "Invalid author ID should return 400 Bad Request");
    }

    @Test()
    public void updateAuthorWithNoChanges() throws Exception {
        Response response = authorAPIObject.updateAuthor(1, 9,"Existing FirstName", "Existing LastName", restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "No changes made to the author");
    }

}
