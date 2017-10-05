package com.brownfield.pss.book.model;

import com.brownfield.pss.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
