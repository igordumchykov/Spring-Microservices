package com.jdum.booking.search.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SEARCH_INVENTORY")
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Inventory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INV_ID")
    private Long id;

    @Column(name = "COUNT")
    private int count;

    public Inventory(int count) {
        this.count = count;
    }
}
