package com.jdum.booking.book.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdum.booking.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "bookingRecord", callSuper = false)
@AllArgsConstructor
public class Passenger extends BaseEntity {

    private String firstName;
    private String lastName;
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    @JsonIgnore
    private BookingRecord bookingRecord;

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + super.id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }


}
