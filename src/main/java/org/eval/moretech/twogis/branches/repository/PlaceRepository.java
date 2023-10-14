package org.eval.moretech.twogis.branches.repository;

import org.eval.moretech.twogis.branches.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
    List<Place> getPlacesByType(Place.PlaceType type);
}
