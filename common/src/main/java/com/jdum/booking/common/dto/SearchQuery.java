package com.jdum.booking.common.dto;


import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SearchQuery {

    private static final String DEFAULT_ORIGIN = "NYC";
    private static final String DEFAULT_DST = "SFO";
    private static final String DEFAULT_DATE = "22-JAN-16";

    @Size(min = 3, max = 255)
    private String origin;

    @Size(min = 3, max = 255)
    private String destination;

    private String tripDate;

    public static SearchQuery getDefault(){
        return new SearchQuery(DEFAULT_ORIGIN, DEFAULT_DST, DEFAULT_DATE);
    }
}