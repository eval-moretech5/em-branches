package org.eval.moretech.twogis.branches.mapper;

import org.eval.moretech.twogis.branches.entity.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class, GeometryFactory.class})
public abstract class BranchMapper {

    @Autowired
    private GeometryFactory geometryFactory;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Mappings({
        @Mapping(source = "shortName", target = "title"),
        @Mapping(source = "special.person", target = "serviceNaturalEntity"),
        @Mapping(source = "special.juridical", target = "serviceLegalEntity"),
        @Mapping(source = "special.ramp", target = "serviceLowMobility"),
        @Mapping(source = "special.vipZone", target = "servicePremium"),
    })
    public abstract Place map(VtbBranch source);

    @Mappings({
        @Mapping(source = "title", target = "name"),
        @Mapping(source = "coordinates", target = "point"),
    })
    public abstract BranchDto map(DistancedPlace place);

    public BranchScheduleDto map(Place place) {

        Map<PersonType, BranchScheduleDto.ScheduleWeek> schedules = new HashMap<>();

        if (Boolean.TRUE.equals(place.getServiceNaturalEntity()) && place.getNaturalEntitySchedule() != null) {
            schedules.put(PersonType.PHYSICAL, map(place.getNaturalEntitySchedule()));
        }

        if (Boolean.TRUE.equals(place.getServiceLegalEntity()) && place.getLegalEntitySchedule() != null) {
            schedules.put(PersonType.LEGAL, map(place.getLegalEntitySchedule()));
        }

        return BranchScheduleDto.builder()
            .id(place.getId())
            .schedules(schedules)
            .build();
    }

    public BranchScheduleDto mapScheduled(DistancedPlace place) {

        Map<PersonType, BranchScheduleDto.ScheduleWeek> schedules = new HashMap<>();

        if (Boolean.TRUE.equals(place.getServiceNaturalEntity()) && place.getNaturalEntitySchedule() != null) {
            schedules.put(PersonType.PHYSICAL, map(place.getNaturalEntitySchedule()));
        }

        if (Boolean.TRUE.equals(place.getServiceLegalEntity()) && place.getLegalEntitySchedule() != null) {
            schedules.put(PersonType.LEGAL, map(place.getLegalEntitySchedule()));
        }

        return BranchScheduleDto.builder()
            .id(place.getId())
            .schedules(schedules)
            .build();
    }

    protected Boolean map(int source) {
        return source == 1;
    }

    protected Point map(VtbBranch.Coordinates source) {
        return geometryFactory.createPoint(new Coordinate(source.getLongitude(), source.getLatitude()));
    }

    protected PointDto map(Point source) {
        return PointDto.builder()
            .lon(source.getX())
            .lat(source.getY())
            .build();
    }

    @AfterMapping
    protected void enrich(VtbBranch source, @MappingTarget Place target) {
        target.setType(Place.PlaceType.BRANCH);
        if (target.getServiceNaturalEntity()) {
            Place.ScheduleWeek schedule = scheduleMapper.map(source.getScheduleFl());
            target.setNaturalEntitySchedule(schedule);
        }
        if (target.getServiceLegalEntity()) {
            Place.ScheduleWeek schedule = scheduleMapper.map(source.getScheduleJurL());
            target.setLegalEntitySchedule(schedule);
        }
        Place.TextSchedule textSchedule = Place.TextSchedule.builder()
            .naturalEntity(source.getScheduleFl())
            .legalEntity(source.getScheduleJurL())
            .build();
        target.setTextSchedule(textSchedule);
    }

    protected BranchScheduleDto.ScheduleWeek map(Place.ScheduleWeek scheduleWeek) {
        BranchScheduleDto.ScheduleWeek week = new BranchScheduleDto.ScheduleWeek();
        week.put(DayOfWeek.MONDAY, map(scheduleWeek.getMonday()));
        week.put(DayOfWeek.TUESDAY, map(scheduleWeek.getTuesday()));
        week.put(DayOfWeek.WEDNESDAY, map(scheduleWeek.getWednesday()));
        week.put(DayOfWeek.THURSDAY, map(scheduleWeek.getThursday()));
        week.put(DayOfWeek.FRIDAY, map(scheduleWeek.getFriday()));
        week.put(DayOfWeek.SATURDAY, map(scheduleWeek.getSaturday()));
        week.put(DayOfWeek.SUNDAY, map(scheduleWeek.getSunday()));
        return week;
    }

    protected abstract BranchScheduleDto.ScheduleDay map(Place.ScheduleDay scheduleDay);

}
