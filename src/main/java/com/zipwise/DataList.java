package com.zipwise;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Generated;

/**
 * This is the DataList class designed as a support class for the zip radius service to hold data list items.
 * @author srevin
 */
@Data
@Generated("com.robohorse.robopojogenerator")
public class DataList{

	@JsonProperty("DataList")
	private List<DataListItem> dataList;
}