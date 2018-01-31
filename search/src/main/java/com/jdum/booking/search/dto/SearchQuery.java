package com.jdum.booking.search.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
public class SearchQuery {
    private String origin;
    private String destination;
    private String flightDate;
}