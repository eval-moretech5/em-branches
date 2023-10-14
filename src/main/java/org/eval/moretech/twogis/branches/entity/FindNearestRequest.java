package org.eval.moretech.twogis.branches.entity;

import lombok.Data;

import java.util.List;

@Data
public class FindNearestRequest {
    private PointDto from;
    private Boolean lowMobility;
    private PersonType personType;
    private List<ServiceType> services;
    private Boolean premium;
    private Integer radius;
    private Integer limit;
}
