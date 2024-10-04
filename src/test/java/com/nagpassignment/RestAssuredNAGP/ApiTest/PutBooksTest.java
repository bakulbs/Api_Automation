package com.nagpassignment.RestAssuredNAGP.ApiTest;
import com.nagpassignment.RestAssuredNAGP.APIObject.*;
import org.testng.annotations.Test;

import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;
import com.nagpassignment.RestAssuredNAGP.Utils.AssertionUtil;

import io.restassured.response.Response;

import static junit.framework.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


public class PutBooksTest extends BaseAPITest {
    private AssertionUtil assertionUtil = new AssertionUtil();
    BooksAPIObject booksAPIObject = new BooksAPIObject();
    CreateBookingAPIObject createBookingAPIObject = new CreateBookingAPIObject();


    @Test()
    public void updateBookSuccessfully() throws Exception {
        Response response = booksAPIObject.updateBook(1, "Updated Title", "Updated Description", 300, "Updated Excerpt", "2024-10-04T05:30:05.154Z", restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "Book updated successfully");
        restParameter.clear();
    }

    @Test()
    public void updateBookWithInvalidId() throws Exception {
        Response response = booksAPIObject.updateBook(-1, "Invalid Title", "Description", 300, "Excerpt", "2024-10-04T05:30:05.154Z", restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 404, "Book not found");
        restParameter.clear();
    }

    @Test()
    public void updateBookWithMaxLengthFields() throws Exception {
        String maxLengthTitle = "A" + "A" + "A" + "A" + "A" + "A" + "A" + "A" + "A" + "A" + // 10 * 25 = 250 characters
                "A" + "A"; // Adding 2 more characters to make it 255
        String maxLengthDescription = "B" + "B" + "B" + "B" + "B" + "B" + "B" + "B" + "B" + "B" + // 10 * 25 = 250 characters
                "B" + "B"; // Adding 2 more characters to make it 255

        Response response = booksAPIObject.updateBook(1, maxLengthTitle, maxLengthDescription, 300, "Excerpt", "2024-10-04T05:30:05.154Z", restParameter);
        assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "Book updated successfully with max length fields");
        restParameter.clear();
    }


@Test()
public void updateBookWithNoChanges() throws Exception {
    Response response = booksAPIObject.updateBook(1, "Existing Title", "Existing Description", 300, "Existing Excerpt", "2024-10-04T05:30:05.154Z",restParameter);
    assertionUtil.verifyResult(extentTest, response.getStatusCode(), 200, "No changes made");
}







}
