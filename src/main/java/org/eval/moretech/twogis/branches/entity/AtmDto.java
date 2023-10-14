package org.eval.moretech.twogis.branches.entity;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AtmDto implements PlaceDto {
    Long id;
    PlaceType type;
    String ownerBank;
    String city;
    String organization;
    String address;
    String workingType;
    Schedule schedule;

    List<ServiceType> services;

    Integer distance;
    Integer lineTime;

    PointDto point;

    @Value
    @Builder
    public static class Schedule {
        String from;
        String till;
    }
}
