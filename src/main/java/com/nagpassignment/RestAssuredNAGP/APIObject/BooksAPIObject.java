package com.nagpassignment.RestAssuredNAGP.APIObject;

import java.util.List;

import com.nagpassignment.RestAssuredNAGP.POJO.BookPOJO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * This class represents an API object for retrieving books.
 */
public class BooksAPIObject extends BaseAPIObject {
    public static final String GET_ALL_BOOKS = "api/v1/Books";
    public static final String GET_BYID_BOOKS = "api/v1/Books/{id}";
    public static final String GET_BOOKS_BY_BOOK_ID = "api/v1/Books/{id}";
public static final String POST_BOOKS_BY_BOOK_NAME = "api/v1/Books";
public static final String POST_BOOKS_BY_BOOK_ID = "api/v1/Books/{id}";
public static final String DELETE_BOOKS_BY_BOOK_ID = "api/v1/Books/{id}";
    /**
     * Retrieves a list of all book IDs.
     *
     * @param restParameter An object containing request parameters.
     * @return              A list of book IDs.
     * @throws Exception    If there is an error during the request.
     */
    public static List<String> getAllBooks(BaseRestParameter restParameter) throws Exception {
        restParameter.setHeader("Content-type", "application/json");
        Response response = get(GET_ALL_BOOKS, restParameter);
        JsonPath jsonPath = new JsonPath(response.asPrettyString());
        return jsonPath.getList("title", String.class);
    }
    public  Response addBook(int id, String title, String description,int pageCount,String excerpt,String publishDate, BaseRestParameter restParameter) throws Exception {
        BookPOJO bookPOJO = new BookPOJO();
        bookPOJO.setId(id);
        bookPOJO.setTitle(title);
        bookPOJO.setDescription(description);
        bookPOJO.setPageCount(pageCount);
        bookPOJO.setExcerpt(excerpt);
        bookPOJO.setPublishDate(publishDate);

        restParameter.setHeader("Content-type", "application/json");

        // Step 1: Make a POST request to add the new book
        Response response = post(POST_BOOKS_BY_BOOK_NAME,restParameter,objectMapper.writeValueAsString(bookPOJO));

        // Step 2: Return the response for further validation if needed
        return response;
    }
    public Response updateBook(int id, String title, String description,int pageCount,String excerpt,String publishDate, BaseRestParameter restParameter) throws Exception {
        BookPOJO bookPOJO = new BookPOJO();
        bookPOJO.setId(id);
        bookPOJO.setTitle(title);
        bookPOJO.setDescription(description);
        bookPOJO.setPageCount(pageCount);
        bookPOJO.setExcerpt(excerpt);
        bookPOJO.setPublishDate(publishDate);
        restParameter.setHeader("Content-type", "application/json");
        Response response = put(POST_BOOKS_BY_BOOK_ID, restParameter, objectMapper.writeValueAsString(bookPOJO));
        return response;
    }
    public Response deleteBook(int id, BaseRestParameter restParameter) throws Exception {
        restParameter.setHeader("Content-type", "application/json");

        Response response = delete(DELETE_BOOKS_BY_BOOK_ID + "/" + id, restParameter);

        return response;
    }



    public Response getBookByIdWithoutAuth(String bookingId, BaseRestParameter restParameter) throws Exception {

        restParameter.setHeader("Content-type", "application/json");

        restParameter.setPath("id", bookingId);

        return get(GET_BOOKS_BY_BOOK_ID, restParameter);
    }
    public List<String> getByBookId(BaseRestParameter restParameter) throws Exception {
        restParameter.setHeader("Content-type", "application/json");
        Response response = get(GET_ALL_BOOKS, restParameter);
        JsonPath jsonPath = new JsonPath(response.asPrettyString());
        return jsonPath.getList("title", String.class);
    }

    /**
     * Retrieves a booking by its ID.
     *
     * @param bookingId     The ID of the booking to be retrieved.
     * @param restParameter An object containing request parameters.
     * @return              The response containing the booking information.
     * @throws Exception    If there is an error during the request.
     */
    public Response getBooksById(String bookingId, BaseRestParameter restParameter) throws Exception {
        restParameter.setHeader("Content-type", "application/json");
        restParameter.setPath("id", bookingId);
        return get(GET_BYID_BOOKS, restParameter);
    }
    public Response getBooksByBookId(String bookingId, BaseRestParameter restParameter) throws Exception {
        restParameter.setHeader("Content-type", "application/json");
        restParameter.setPath("id", bookingId);
        return get(GET_BOOKS_BY_BOOK_ID, restParameter);
    }
    public Response getBooksByIdWithoutAuth(String bookingId, BaseRestParameter restParameter) throws Exception {

        restParameter.setHeader("Content-type", "application/json");


        restParameter.setPath("id", bookingId);


        return get(GET_BYID_BOOKS, restParameter);
    }
}
