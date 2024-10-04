package com.nagpassignment.RestAssuredNAGP.APIObject;

import java.util.Date;
import java.util.List;

import com.nagpassignment.RestAssuredNAGP.POJO.AuthorPOJO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * This class represents an API object for retrieving bookings.
 */
public class AuthorAPIObject extends BaseAPIObject {
    public static final String GET_ALL_BOOKING = "api/v1/Authors";
    public static final String GET_BYID_BOOKING = "api/v1/Authors/{id}";
    public static final String GET_BOOKING_BY_BOOK_ID = "api/v1/Authors/authors/books/{id}";
    public static final String POST_AUTHORS=  "api/v1/Authors";
    public static final String UPDATE_AUTHORS = "api/v1/Authors";
public static final String DELETE_AUTHORS = "api/v1/Authors/authors/books";
    /**
     * Retrieves a list of all booking IDs.
     *
     * @param restParameter An object containing request parameters.
     * @return              A list of booking IDs.
     * @throws Exception    If there is an error during the request.
     */
    public List<String> getAllBookings(BaseRestParameter restParameter) throws Exception {
        restParameter.setHeader("Content-type", "application/json");
        Response response = get(GET_ALL_BOOKING, restParameter);
        JsonPath jsonPath = new JsonPath(response.asPrettyString());
        return jsonPath.getList("title", String.class);
    }
    public List<String> getByBookId(BaseRestParameter restParameter) throws Exception {
        restParameter.setHeader("Content-type", "application/json");
        Response response = get(GET_ALL_BOOKING, restParameter);
        JsonPath jsonPath = new JsonPath(response.asPrettyString());
        return jsonPath.getList("title", String.class);
    }
    public Response addAuthor(int id, int idBook, String firstName, String lastName,BaseRestParameter restParameter) throws Exception {
        AuthorPOJO authorPOJO = new AuthorPOJO();
        authorPOJO.setId(id);
        authorPOJO.setIdBook(idBook);
        authorPOJO.setFirstName(firstName);
        authorPOJO.setLastName(lastName);
        restParameter.setHeader("Content-type", "application/json");
        return post(POST_AUTHORS, restParameter, objectMapper.writeValueAsString(authorPOJO));

    }
    public Response updateAuthor(int id,int idBook, String firstName, String lastName, BaseRestParameter restParameter) throws Exception {
        AuthorPOJO authorPOJO = new AuthorPOJO();
        authorPOJO.setId(id);
        authorPOJO.setIdBook(idBook);
        authorPOJO.setFirstName(firstName);
        authorPOJO.setLastName(lastName);
        restParameter.setHeader("Content-type", "application/json");
        return put(UPDATE_AUTHORS + "/" + id, restParameter, objectMapper.writeValueAsString(authorPOJO));
    }

    public Response deleteAuthor(int id, BaseRestParameter restParameter) throws Exception {
        restParameter.setHeader("Content-type", "application/json");
        Response response = delete(DELETE_AUTHORS + "/" + id, restParameter);
        return response;
    }


    /**
     * Retrieves a booking by its ID.
     *
     * @param bookingId     The ID of the booking to be retrieved.
     * @param restParameter An object containing request parameters.
     * @return              The response containing the booking information.
     * @throws Exception    If there is an error during the request.
     */
    public Response getBookingById(String bookingId, BaseRestParameter restParameter) throws Exception {
        restParameter.setHeader("Content-type", "application/json");
        restParameter.setPath("id", bookingId);
        return get(GET_BYID_BOOKING, restParameter);
    }
    public Response getBookingByBookId(String bookingId, BaseRestParameter restParameter) throws Exception {
        restParameter.setHeader("Content-type", "application/json");
        restParameter.setPath("id", bookingId);
        return get(GET_BOOKING_BY_BOOK_ID, restParameter);
    }
    public Response getBookingByIdWithoutAuth(String bookingId, BaseRestParameter restParameter) throws Exception {

        restParameter.setHeader("Content-type", "application/json");
        restParameter.setPath("id", bookingId);
        return get(GET_BYID_BOOKING, restParameter);
    }
}
