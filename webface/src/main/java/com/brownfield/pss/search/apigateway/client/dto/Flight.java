package com.brownfield.pss.search.apigateway.client.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Flight {

  	private long id;

	private String flightNumber;
	private String origin;
	private String destination;
	private String flightDate;

	private Fares fares;

	public Flight(String flightNumber, String origin, String destination, String flightDate, Fares fares) {
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.flightDate = flightDate;
		this.fares = fares;
	}
	
}
