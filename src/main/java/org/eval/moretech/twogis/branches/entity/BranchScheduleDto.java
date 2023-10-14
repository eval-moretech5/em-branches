package org.eval.moretech.twogis.branches.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Value
@Builder
public class BranchScheduleDto {
    Long id;

    Map<PersonType, ScheduleWeek> schedules;

    public static class ScheduleWeek extends HashMap<DayOfWeek, ScheduleDay> {
    }

    @Value
    @Builder
    public static class ScheduleDay {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime from;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime till;
    }
}
