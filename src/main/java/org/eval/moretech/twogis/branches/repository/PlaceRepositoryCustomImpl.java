package org.eval.moretech.twogis.branches.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.eval.moretech.twogis.branches.config.PlacesRequestProperties;
import org.eval.moretech.twogis.branches.entity.DistancedPlace;
import org.eval.moretech.twogis.branches.entity.FindNearestRequest;
import org.eval.moretech.twogis.branches.entity.PersonType;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PlaceRepositoryCustomImpl implements PlaceRepositoryCustom {

    @PersistenceContext
    private final EntityManager em;
    private final GeometryFactory geometryFactory;
    private final PlacesRequestProperties properties;

    @Override
    public List<DistancedPlace> findNearest(FindNearestRequest request) {

        Query query = em.createNativeQuery(
    "select st_distance(p.coordinates, :point)  distance, p.* " +
            "from branches.place p " +
            "where st_distance(p.coordinates, :point) < :radius " +
            "and ((" + getBranchCondition(request) + ") or (" + getAtmCondition(request) + ")) " +
            "and (" + getServicesCondition(request) + ") " +
            "ORDER BY distance " +
            "limit :limit ",
            DistancedPlace.class
        );

        query.setParameter("point", geometryFactory.createPoint(new CoordinateXY(request.getFrom().getLon(), request.getFrom().getLat())));
        query.setParameter("radius",
            Objects.nonNull(request.getRadius())
                ? request.getRadius()
                : properties.getRadius()
        );
        query.setParameter("limit",
            Objects.nonNull(request.getLimit())
                ? request.getLimit()
                : properties.getLimit()
        );

        return query.getResultList();
    }

    private String getBranchCondition(FindNearestRequest request) {
        String personTypeCondition = PersonType.LEGAL.equals(request.getPersonType())
            ? "p.service_legal_entity"
            : "p.service_natural_entity";
        String branchCondition = "p.place_type='BRANCH' and " + personTypeCondition + "";

        if (Boolean.TRUE.equals(request.getLowMobility())) {
            branchCondition += " and p.service_low_mobility";
        }

        if (Boolean.TRUE.equals(request.getPremium())) {
            branchCondition += " and p.service_premium";
        }
        return branchCondition;
    }

    private String getAtmCondition(FindNearestRequest request) {
        if (Boolean.TRUE.equals(request.getLowMobility()) || PersonType.LEGAL.equals(request.getPersonType()) ) {
            return "false";
        }
        return "p.place_type='ATM'";
    }

    private String getServicesCondition(FindNearestRequest request) {

        if (CollectionUtils.isEmpty(request.getServices())) {
            return "true";
        }

        String servicesSequence = request.getServices().stream()
            .map(s -> "'" + s + "'")
            .collect(Collectors.joining(","));

        return "select count(value) = " + request.getServices().size() + " " +
            "from jsonb_array_elements_text(p.services) " +
            "where value in (" + servicesSequence + ") ";
    }
}
