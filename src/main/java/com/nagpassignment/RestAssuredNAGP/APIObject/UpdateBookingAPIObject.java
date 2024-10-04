package com.nagpassignment.RestAssuredNAGP.APIObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import io.restassured.response.Response;

/**
 * This class represents an API object for updating bookings.
 */
public class UpdateBookingAPIObject extends BaseAPIObject {

    private static final String UPDATE_BOOKING_URI = "booking/{id}";

    /**
     * Updates a booking with the specified ID and provided details.
     *
     * @param bookingId      The ID of the booking to be updated.
     * @param firstName      The updated first name of the guest (nullable).
     * @param lastName       The updated last name of the guest (nullable).
     * @param totalprice     The updated total price of the booking (nullable).
     * @param depositpaid    Whether a deposit is paid or not (nullable).
     * @param additionalneeds Updated additional needs for the booking (nullable).
     * @param checkin        The updated check-in date for the booking (nullable).
     * @param checkout       The updated check-out date for the booking (nullable).
     * @param restParameter  An object containing request parameters.
     * @return               The response from the update booking request.
     * @throws Exception     If there is an error during the request.
     */
    public Response updateBooking(String bookingId, String firstName, String lastName, Double totalprice, Boolean depositpaid,
                                  String additionalneeds, String checkin, String checkout, BaseRestParameter restParameter) throws Exception {
        // Create ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Create an object to hold non-null properties
        Map<String, Object> bookingData = new HashMap<>();

        if (firstName != null) {
            bookingData.put("firstname", firstName);
        }

        if (lastName != null) {
            bookingData.put("lastname", lastName);
        }

        if (depositpaid != null) {
            bookingData.put("depositpaid", depositpaid);
        }

        if (totalprice != null) {
            bookingData.put("totalprice", totalprice);
        }

        if (additionalneeds != null) {
            bookingData.put("additionalneeds", additionalneeds);
        }

        if (checkin != null || checkout != null) {
            Map<String, Object> bookingdates = new HashMap<>();
            if (checkin != null) {
                bookingdates.put("checkin", checkin);
            }
            if (checkout != null) {
                bookingdates.put("checkout", checkout);
            }
            bookingData.put("bookingdates", bookingdates);
        }

        // Serialize the bookingData map to JSON
        String jsonPayload = objectMapper.writeValueAsString(bookingData);

        restParameter.setHeader("Content-type", "application/json");
        restParameter.setPath("id", bookingId);

        return put(UPDATE_BOOKING_URI, restParameter, jsonPayload);
    }

    /**
     * Partially updates a booking with the specified ID and provided details.
     *
     * @param bookingId      The ID of the booking to be partially updated.
     * @param firstName      The updated first name of the guest (nullable).
     * @param lastName       The updated last name of the guest (nullable).
     * @param totalprice     The updated total price of the booking (nullable).
     * @param depositpaid    Whether a deposit is paid or not (nullable).
     * @param additionalneeds Updated additional needs for the booking (nullable).
     * @param checkin        The updated check-in date for the booking (nullable).
     * @param checkout       The updated check-out date for the booking (nullable).
     * @param restParameter  An object containing request parameters.
     * @return               The response from the partial update booking request.
     * @throws Exception     If there is an error during the request.
     */
    public Response partialUpdateBooking(String bookingId, String firstName, String lastName, Double totalprice,
                                          Boolean depositpaid, String additionalneeds, String checkin, String checkout,
                                          BaseRestParameter restParameter) throws Exception {
        // Create ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Create an object to hold non-null properties
        Map<String, Object> bookingData = new HashMap<>();

        if (firstName != null) {
            bookingData.put("firstname", firstName);
        }

        if (lastName != null) {
            bookingData.put("lastname", lastName);
        }

        if (depositpaid != null) {
            bookingData.put("depositpaid", depositpaid);
        }

        if (totalprice != null) {
            bookingData.put("totalprice", totalprice);
        }

        if (additionalneeds != null) {
            bookingData.put("additionalneeds", additionalneeds);
        }

        if (checkin != null || checkout != null) {
            Map<String, Object> bookingdates = new HashMap<>();
            if (checkin != null) {
                bookingdates.put("checkin", checkin);
            }
            if (checkout != null) {
                bookingdates.put("checkout", checkout);
            }
            bookingData.put("bookingdates", bookingdates);
        }

        // Serialize the bookingData map to JSON
        String jsonPayload = objectMapper.writeValueAsString(bookingData);

        restParameter.setHeader("Content-type", "application/json");
        restParameter.setPath("id", bookingId);

        return patch(UPDATE_BOOKING_URI, restParameter, jsonPayload);
    }
}
