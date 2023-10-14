package org.eval.moretech.twogis.branches.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Value
@Builder
public class BranchDto implements PlaceDto {
    Long id;
    PlaceType type;
    String name;
    String address;
    String city;
    Boolean serviceNaturalEntity;
    Boolean serviceLegalEntity;
    Boolean serviceLowMobility;
    Boolean servicePremium;
    List<ServiceType> services;

    ScheduleWeek naturalEntitySchedule;
    ScheduleWeek legalEntitySchedule;
    TextSchedule textSchedule;

    Integer distance;
    Integer lineTime;

    PointDto point;

    @Value
    @Builder
    public static class TextSchedule {
        String naturalEntity;
        String legalEntity;
    }

    @Value
    @Builder
    public static class ScheduleWeek {
        ScheduleDay monday;
        ScheduleDay tuesday;
        ScheduleDay wednesday;
        ScheduleDay thursday;
        ScheduleDay friday;
        ScheduleDay saturday;
        ScheduleDay sunday;
    }

    @Value
    @Builder
    public static class ScheduleDay {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
        LocalTime from;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
        LocalTime till;
    }
}
