package com.nagpassignment.RestAssuredNAGP.APIObject;

import io.restassured.response.Response;

/**
 * This class represents an API object for deleting bookings.
 */
public class DeleteBookingAPIObject extends BaseAPIObject {

    private static final String DELETE_BOOKING_URI = "booking/{id}";

    /**
     * Deletes a booking with the specified ID.
     *
     * @param bookingId      The ID of the booking to be deleted.
     * @param restParameter  An object containing request parameters.
     * @return               The response from the delete booking request.
     * @throws Exception     If there is an error during the request.
     */
    public Response deleteBooking(String bookingId, BaseRestParameter restParameter) throws Exception {
        // Set the path parameter for the booking ID
        restParameter.setPath("id", bookingId);

        // Perform the DELETE request to delete the booking
        return delete(DELETE_BOOKING_URI, restParameter);
    }
}
