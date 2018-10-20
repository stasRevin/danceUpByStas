package com.zipwise;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Generated;

@Data
@Generated("com.robohorse.robopojogenerator")
public class DataList{

	@JsonProperty("DataList")
	private List<DataListItem> dataList;
}