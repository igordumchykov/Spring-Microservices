package com.brownfield.pss.search.apigateway.fares.model;

import com.brownfield.pss.search.apigateway.common.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Fare  extends BaseEntity {
    private String flightNumber;
    private String flightDate;
    private String fare;
}
