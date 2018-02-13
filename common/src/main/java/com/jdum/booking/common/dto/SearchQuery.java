package com.jdum.booking.common.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SearchQuery {

    private static final String DEFAULT_ORIGIN = "NYC";
    private static final String DEFAULT_DST = "SFO";
    private static final String DEFAULT_DATE = "22-JAN-16";

    private String origin;
    private String destination;
    private String tripDate;

    public static SearchQuery getDefault(){
        return new SearchQuery(DEFAULT_ORIGIN, DEFAULT_DST, DEFAULT_DATE);
    }
}