package com.zipwise;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Generated;

@Data
@Generated("com.robohorse.robopojogenerator")
public class DataListItem{

	@JsonProperty("State")
	private String state;

	@JsonProperty("Latitude")
	private double latitude;

	@JsonProperty("City")
	private String city;

	@JsonProperty("Code")
	private String code;

	@JsonProperty("Longitude")
	private double longitude;

	@JsonProperty("County")
	private String county;

	@JsonProperty("Distance")
	private double distance;
}