package org.eval.moretech.twogis.branches.repository;

import org.eval.moretech.twogis.branches.entity.DistancedPlace;
import org.eval.moretech.twogis.branches.entity.FindNearestRequest;

import java.util.List;

public interface PlaceRepositoryCustom {
    List<DistancedPlace> findNearest(FindNearestRequest request);
}
