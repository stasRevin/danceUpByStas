package com.zipwise;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Generated;

/**
 * This is the DataListItem zip radius web service support class designed to describe a JSON string to be returned
 * by the service.
 * @author srevin
 */
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