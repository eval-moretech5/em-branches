package org.eval.moretech.twogis.branches.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PoorPlaceDto {
    Long id;
    Integer lineTime;
    PointDto point;
}
