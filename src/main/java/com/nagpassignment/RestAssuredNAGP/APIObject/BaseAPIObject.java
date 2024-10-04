
package com.nagpassignment.RestAssuredNAGP.APIObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.UUID;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagpassignment.RestAssuredNAGP.APIObject.BaseRestParameter;
import com.nagpassignment.RestAssuredNAGP.Base.BaseAPITest;
import com.nagpassignment.RestAssuredNAGP.Utils.JSONUtil;

/**
 * This is the base class for API objects that contains common methods for HTTP actions.
 */
public class BaseAPIObject extends BaseAPITest {
	protected  ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Constructor to set the base URI.
	 */
	public BaseAPIObject() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
	}

	/**
     * Performs a GET request.
     *
     * @param endpoint       The API endpoint to request.
     * @param restParameter  An object containing request parameters.
     * @return               The response from the GET request.
     */
	public static Response get(String endpoint, BaseRestParameter restParameter) {
		logRequestInfo("GET",endpoint,restParameter); 
		Response response  = (Response) RestAssured.given()
				.headers(restParameter.getHeader())
				.queryParams(restParameter.getQuery())
				.pathParams(restParameter.getPath())
				.when()
				.get(endpoint);

		logResponseInfo(response);
		return response;
	}
	
	 /**
     * Performs a POST request.
     *
     * @param endpoint       The API endpoint to request.
     * @param restParameter  An object containing request parameters.
     * @param requestBody    The request body for the POST request.
     * @return               The response from the POST request.
     */
	public Response post(String endpoint, BaseRestParameter restParameter, Object requestBody) {
		logRequestInfo("POST",endpoint,restParameter,requestBody.toString());
		Response response = (Response) RestAssured.given()
				.headers(restParameter.getHeader())
				.queryParams(restParameter.getQuery())
				.pathParams(restParameter.getPath())
				.body(requestBody).log().all()
				.when()
				.post(endpoint);
		logResponseInfo(response);

		return response;
	}
	
	 /**
     * Performs a PUT request.
     *
     * @param endpoint       The API endpoint to request.
     * @param restParameter  An object containing request parameters.
     * @param requestBody    The request body for the POST request.
     * @return               The response from the POST request.
     */
	public Response put(String endpoint, BaseRestParameter restParameter, Object requestBody) {
		logRequestInfo("PUT",endpoint,restParameter,requestBody.toString());
		Response response = (Response) RestAssured.given()
				.headers(restParameter.getHeader())
				.queryParams(restParameter.getQuery())
				.pathParams(restParameter.getPath())
				.body(requestBody).log().all()
				.when()
				.put(endpoint);
		logResponseInfo(response);
		return response;
	}
	
	 /**
     * Performs a PATCH request.
     *
     * @param endpoint       The API endpoint to request.
     * @param restParameter  An object containing request parameters.
     * @param requestBody    The request body for the POST request.
     * @return               The response from the POST request.
     */
	public Response patch(String endpoint, BaseRestParameter restParameter, Object requestBody) {
		logRequestInfo("PATCH",endpoint,restParameter,requestBody.toString());
		Response response = (Response) RestAssured.given()
				.headers(restParameter.getHeader())
				.queryParams(restParameter.getQuery())
				.pathParams(restParameter.getPath())
				.body(requestBody).log().all()
				.when()
				.patch(endpoint);
		logResponseInfo(response);
		return response;
	}
	
	 /**
     * Performs a DELETE request.
     *
     * @param endpoint       The API endpoint to request.
     * @param restParameter  An object containing request parameters.

     * @return               The response from the POST request.
     */
	public Response delete(String endpoint, BaseRestParameter restParameter) {
		logRequestInfo("DELETE",endpoint,restParameter); 
		Response response = (Response) RestAssured.given()
				.headers(restParameter.getHeader())
				.queryParams(restParameter.getQuery())
				.pathParams(restParameter.getPath())
				.when()
				.delete(endpoint);
		logResponseInfo(response);
		return response;
	}
	
	 /**
     * Logs request information with JSON payload.
     *
     * @param httpMethod    The HTTP method used in the request.
     * @param endpoint      The API endpoint being called.
     * @param restParameter An object containing request parameters.
     * @param jsonPayLoad   The JSON payload of the request.
     */
	protected void logRequestInfo(String httpMethod, String endpoint, BaseRestParameter restParameter, String jsonPayLoad) {
		extentTest.log(Status.INFO, MarkupHelper.createLabel("REQUEST INFO", ExtentColor.AMBER) );
		logger.info("REQUEST INFO");
		extentTest.log(Status.INFO, MarkupHelper.createLabel("HTTP METHOD : "+ httpMethod, ExtentColor.GREY) );
		logger.info("HTTP METHOD : "+ httpMethod);
		extentTest.log(Status.INFO, MarkupHelper.createLabel("ENDPOINT : "+ endpoint, ExtentColor.GREY)  );
		logger.info("ENDPOINT : "+ endpoint);
		extentTest.log(Status.INFO, MarkupHelper.createLabel("HEADERS : "+  restParameter.getHeader().toString(), ExtentColor.GREY));
		logger.info("HEADERS : "+  restParameter.getHeader().toString());
		extentTest.log(Status.INFO, MarkupHelper.createLabel("QUERY PARAMS : " + restParameter.getQuery().toString(), ExtentColor.GREY) );
		logger.info("QUERY PARAMS : " + restParameter.getQuery().toString());
		extentTest.log(Status.INFO, MarkupHelper.createLabel("PATH PARAMS : " + restParameter.getPath().toString(), ExtentColor.GREY) );
		logger.info("PATH PARAMS : " + restParameter.getPath().toString());
		extentTest.log(Status.INFO, MarkupHelper.createLabel("REQUEST PAYLOAD :" + jsonPayLoad , ExtentColor.GREY) );
		logger.info("REQUEST PAYLOAD :" + jsonPayLoad);

	}
	
	
	protected static void logRequestInfo(String httpMethod, String endpoint, BaseRestParameter restParameter) {
		extentTest.log(Status.INFO, MarkupHelper.createLabel("REQUEST INFO", ExtentColor.AMBER) );
		logger.info("REQUEST INFO");
		extentTest.log(Status.INFO, MarkupHelper.createLabel("HTTP METHOD : "+ httpMethod, ExtentColor.GREY) );
		logger.info("HTTP METHOD : "+ httpMethod);
		extentTest.log(Status.INFO, MarkupHelper.createLabel("ENDPOINT : "+ endpoint, ExtentColor.GREY)  );
		logger.info("ENDPOINT : "+ endpoint);
		extentTest.log(Status.INFO, MarkupHelper.createLabel("HEADERS : "+  restParameter.getHeader().toString(), ExtentColor.GREY));
		logger.info("HEADERS : "+  restParameter.getHeader().toString());
		extentTest.log(Status.INFO, MarkupHelper.createLabel("QUERY PARAMS : " + restParameter.getQuery().toString(), ExtentColor.GREY) );
		logger.info("QUERY PARAMS : " + restParameter.getQuery().toString());
		extentTest.log(Status.INFO, MarkupHelper.createLabel("PATH PARAMS : " + restParameter.getPath().toString(), ExtentColor.GREY) );
		logger.info("PATH PARAMS : " + restParameter.getPath().toString()); 	
	}
	
	 /**
     * Logs response information.
     *
     * @param response The response object to be logged.
     */

	protected static void logResponseInfo(Response response) {
		extentTest.log(Status.INFO, MarkupHelper.createLabel("RESPONSE INFO", ExtentColor.AMBER) );
		extentTest.log(Status.INFO, MarkupHelper.createLabel("STATUS CODE: "+ String.valueOf(response.getStatusCode()) , ExtentColor.GREY) );
		logger.info("STATUS CODE: "+ String.valueOf(response.getStatusCode()));
		extentTest.log(Status.INFO, MarkupHelper.createLabel("RESPONSE PAYLOAD : " + response.asPrettyString() , ExtentColor.GREY)  );
		logger.info("RESPONSE PAYLOAD : " + response.asPrettyString());


	}
	public  String createJsonLink(String jsonContent, String linkText) {
		String uniqueLinkId = "json-link-" + System.currentTimeMillis();
		return "<a id=\"" + uniqueLinkId + "\" href=\"javascript:void(0);\" onclick=\"document.getElementById('" + uniqueLinkId + "').innerText = '" + jsonContent + "';\">" + linkText + "</a>";
	}
	public static Markup createClickableJsonLink( String jsonContent, String linkText) {
		// Generate a unique identifier for the link
		String uniqueLinkId = "json-link-" + UUID.randomUUID().toString();

		// Create a unique label for the link
		String label = "<a href=\"data:text/json;charset=utf-8," + jsonContent + "\" target=\"_blank\">" + linkText + "</a>";

		// Log the label as a clickable link
		extentTest.log(Status.INFO, MarkupHelper.createLabel(label, ExtentColor.BLUE));

		return MarkupHelper.createLabel(uniqueLinkId, ExtentColor.BLUE);
	}
	protected void logJsonAsLink(String jsonContent, String linkText) {
		Markup jsonLink = createClickableJsonLink( jsonContent, linkText);
		// You can use the returned Markup if needed, but it's not required
	}
	
	 /**
     * Create authorization token.
     *
     * @param restParameter n object containing request parameters.
     */

	public String getAuthorisationToken(BaseRestParameter restParameter) {

		Response response = RestAssured.given()
				.headers(restParameter.getHeader())
				.queryParams(restParameter.getQuery())
				.pathParams(restParameter.getPath())
				.body("{\r\n"
						+ "    \"username\" : \"admin\",\r\n"
						+ "    \"password\" : \"password123\"\r\n"
						+ "}")
				.when()
				.post("auth");

		return JSONUtil.getNodeValue(response.asPrettyString(), "token");

	}
}
