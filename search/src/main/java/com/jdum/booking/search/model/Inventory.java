package com.jdum.booking.search.model;

import com.jdum.booking.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "SEARCH_INVENTORY")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "INV_ID"))
public class Inventory extends BaseEntity {

    @Column(name = "COUNT")
    private int count;

    public Inventory(int count) {
        this.count = count;
    }
}
