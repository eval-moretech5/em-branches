package org.eval.moretech.twogis.branches.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(schema = "branches", name = "place")
@Data
public class Place {

    /* Common fields */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "place_type")
    @Enumerated(EnumType.STRING)
    private PlaceType type;
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
    private ScheduleWeek naturalEntitySchedule;

    @Column(name = "legal_entity_schedule")
    @Type(JsonBinaryType.class)
    private ScheduleWeek legalEntitySchedule;

    @Column(name = "text_schedule")
    @Type(JsonBinaryType.class)
    private TextSchedule textSchedule;

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
    private Schedule schedule;

    public enum PlaceType {
        BRANCH, ATM
    }

    @Data
    @Builder
    public static class TextSchedule {
        private String naturalEntity;
        private String legalEntity;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScheduleWeek {
        private ScheduleDay monday;
        private ScheduleDay tuesday;
        private ScheduleDay wednesday;
        private ScheduleDay thursday;
        private ScheduleDay friday;
        private ScheduleDay saturday;
        private ScheduleDay sunday;
    }

    @Data
    @Builder
    public static class ScheduleDay {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
        private LocalTime from;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
        private LocalTime till;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Schedule {
        private String from;
        private String till;
    }
}
