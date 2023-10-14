package org.eval.moretech.twogis.branches.mapper;

import org.eval.moretech.twogis.branches.entity.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GeometryFactory.class})
public abstract class AtmMapper {

    @Autowired
    private GeometryFactory geometryFactory;

    @Mappings({
        @Mapping(source = "coordinates", target = "point")
    })
    public abstract AtmDto map(DistancedPlace place);

    public abstract List<Place> map(List<VtbAtm> source);

    public abstract Place map(VtbAtm source);

    protected PointDto map(Point source) {
        return PointDto.builder()
            .lon(source.getX())
            .lat(source.getY())
            .build();
    }

    @AfterMapping
    protected void enrich(VtbAtm source, @MappingTarget Place target) {
        target.setType(Place.PlaceType.ATM);
        target.setCoordinates(geometryFactory.createPoint(new Coordinate(source.getLongitude(), source.getLatitude())));
        target.setWorkingType(source.getWorking().getStatus());
        target.setSchedule(new Place.Schedule(
            source.getWorking().getFrom(),
            source.getWorking().getTill()
        ));
    }
}
