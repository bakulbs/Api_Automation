package com.nagpassignment.RestAssuredNAGP.POJO;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CreateBookingPOJO.CustomCreateBookingSerializer.class)

public class CreateBookingPOJO {

    private String firstname;
    private String lastname;
    private Double totalprice;
    private Boolean depositpaid;
    private String additionalneeds;

    @JsonProperty("bookingdates")
    private BookingDates bookingdates;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }
    
    public static class CustomCreateBookingSerializer extends JsonSerializer<CreateBookingPOJO> {
        @Override
        public void serialize(CreateBookingPOJO value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            if (value.getFirstname() != null) {
                gen.writeStringField("firstname", value.getFirstname());
            }
            if (value.getLastname() != null) {
                gen.writeStringField("lastname", value.getLastname());
            }
            if (value.getTotalprice() != null) {
                gen.writeNumberField("totalprice", value.getTotalprice());
            }
            if (value.getDepositpaid() != null) {
                gen.writeBooleanField("depositpaid", value.getDepositpaid());
            }
            if (value.getAdditionalneeds() != null) {
                gen.writeStringField("additionalneeds", value.getAdditionalneeds());
            }
            if (value.getBookingdates() != null) {
                BookingDates bookingdates = value.getBookingdates();
                gen.writeObjectFieldStart("bookingdates");
                if (bookingdates.getCheckin() != null) {
                    gen.writeStringField("checkin", bookingdates.getCheckin());
                }
                if (bookingdates.getCheckout() != null) {
                    gen.writeStringField("checkout", bookingdates.getCheckout());
                }
                gen.writeEndObject();
            }
            gen.writeEndObject();
        }
    }
    
}
