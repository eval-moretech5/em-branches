package org.eval.moretech.twogis.branches.entity;

public interface PlaceDto {
    Long getId();
    PlaceType getType();
    Integer getDistance();
    Integer getLineTime();

    enum PlaceType {
        BRANCH, ATM
    }
}
