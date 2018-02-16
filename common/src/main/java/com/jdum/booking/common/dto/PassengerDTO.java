package com.jdum.booking.common.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "bookingRecord")
@ToString(callSuper = true, exclude = "bookingRecord")
public class PassengerDTO extends BaseDTO {

    private String firstName;
    private String lastName;
    private String gender;

    @JsonIgnore
    private BookingRecordDTO bookingRecord;

    public PassengerDTO(String firstName, String lastName, String gender, BookingRecordDTO bookingRecord) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.bookingRecord = bookingRecord;
    }

}
