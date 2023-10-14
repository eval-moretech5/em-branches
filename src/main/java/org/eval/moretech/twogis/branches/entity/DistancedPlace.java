package org.eval.moretech.twogis.branches.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;

import java.util.Set;

/*
* Это полная копия (наследовать нельзя) класса Place. Но тут ещё есть поле distance.
* distance - это поле, которого в таблице нет, оно генерируется в запросе.
* */
@Entity
@Table(schema = "branches", name = "place")
@Data
public class DistancedPlace {

    /* Common fields */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "place_type")
    @Enumerated(EnumType.STRING)
    private Place.PlaceType type;
    @Column(name = "city")
    private String city;
    @Column(name = "address")
    private String address;
    @Column(name = "coordinates")
    private Point coordinates;
    @Column(name = "services")
    @Type(JsonBinaryType.class)
    private Set<ServiceType> services;

    /* Branch fields */
    @Column(name = "title")
    private String title;
    @Column(name = "service_natural_entity")
    private Boolean serviceNaturalEntity;
    @Column(name = "service_legal_entity")
    private Boolean serviceLegalEntity;
    @Column(name = "service_low_mobility")
    private Boolean serviceLowMobility;
    @Column(name = "service_premium")
    private Boolean servicePremium;

    @Column(name = "natural_entity_schedule")
    @Type(JsonBinaryType.class)
    private Place.ScheduleWeek naturalEntitySchedule;

    @Column(name = "legal_entity_schedule")
    @Type(JsonBinaryType.class)
    private Place.ScheduleWeek legalEntitySchedule;

    @Column(name = "text_schedule")
    @Type(JsonBinaryType.class)
    private Place.TextSchedule textSchedule;

    @Column(name = "line_time")
    private Integer lineTime;

    /* Atm fields */
    @Column(name = "owner_bank")
    private String ownerBank;
    @Column(name = "organization")
    private String organization;
    @Column(name = "working_type")
    private String workingType;
    @Column(name = "schedule")
    @Type(JsonBinaryType.class)
    private Place.Schedule schedule;
    @Column(name = "distance")
    private Double distance;
}
