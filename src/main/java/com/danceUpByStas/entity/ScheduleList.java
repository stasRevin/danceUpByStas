package com.danceUpByStas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ScheduleList {

    @JsonProperty("scheduleList")
    private List<Schedule> scheduleList;
}
