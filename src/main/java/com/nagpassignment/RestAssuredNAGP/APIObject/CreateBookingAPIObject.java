package com.nagpassignment.RestAssuredNAGP.APIObject;


import com.nagpassignment.RestAssuredNAGP.POJO.BookingDates;
import com.nagpassignment.RestAssuredNAGP.POJO.CreateBookingPOJO;
import io.restassured.response.Response;

/**
 * This class represents an API object for creating bookings.
 */
public class CreateBookingAPIObject extends BaseAPIObject {

    /**
     * Creates a booking with the specified details.
     *
     * @param firstName       The first name of the guest.
     * @param lastName        The last name of the guest.
     * @param totalprice      The total price of the booking.
     * @param depositpaid     Whether a deposit is paid or not.
     * @param additionalneeds Any additional needs for the booking.
     * @param checkin         The check-in date for the booking.
     * @param checkout        The check-out date for the booking.
     * @param restParameter   An object containing request parameters.
     * @return                The response from the create booking request.
     * @throws Exception      If there is an error during the request.
     */
    public Response createBooking(String firstName, String lastName, Double totalprice, Boolean depositpaid,
                                  String additionalneeds, String checkin, String checkout, BaseRestParameter restParameter) throws Exception {
        // Create a new booking object
        CreateBookingPOJO createBookingPOJO = new CreateBookingPOJO();
        
        // Create booking dates object
        BookingDates bookingdates = new BookingDates();
        
        // Set booking details
        createBookingPOJO.setFirstname(firstName);
        createBookingPOJO.setLastname(lastName);
        createBookingPOJO.setDepositpaid(depositpaid);
        createBookingPOJO.setTotalprice(totalprice);
        createBookingPOJO.setAdditionalneeds(additionalneeds);
        
        // Set booking dates
        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(checkout);
        createBookingPOJO.setBookingdates(bookingdates);
        
        
        // Perform the POST request to create a booking
        return post("booking", restParameter, objectMapper.writeValueAsString(createBookingPOJO));
    }
}
