package com.jdum.booking.book.model;

import com.jdum.booking.common.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends BaseEntity {

    private String busNumber;
    private String tripDate;
    private int available;

    public boolean isAvailable(int count) {
        return ((available - count) > 5);
    }

    public int getBookableInventory() {
        return available - 5;
    }
}
