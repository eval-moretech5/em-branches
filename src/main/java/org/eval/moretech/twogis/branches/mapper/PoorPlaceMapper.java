package org.eval.moretech.twogis.branches.mapper;

import org.eval.moretech.twogis.branches.entity.DistancedPlace;
import org.eval.moretech.twogis.branches.entity.PoorPlaceDto;
import org.eval.moretech.twogis.branches.entity.PointDto;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class, GeometryFactory.class})
public abstract class PoorPlaceMapper {

    @Mappings({
        @Mapping(source = "coordinates", target = "point"),
    })
    public abstract PoorPlaceDto map(DistancedPlace source);

    public abstract List<PoorPlaceDto> map(List<DistancedPlace> source);

    protected PointDto map(Point source) {
        return PointDto.builder()
            .lon(source.getX())
            .lat(source.getY())
            .build();
    }
}
