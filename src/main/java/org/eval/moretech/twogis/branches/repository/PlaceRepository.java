package org.eval.moretech.twogis.branches.repository;

import org.eval.moretech.twogis.branches.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
}
