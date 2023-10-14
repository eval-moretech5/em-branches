package org.eval.moretech.twogis.branches.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VtbBranch {
    private Long id;
    @JsonProperty("Biskvit_id")
    private String biscuitId;
    private String shortName;
    private String address;
    private String city;
    private String scheduleFl;
    private String scheduleJurL;
    private Special special;
    private Coordinates coordinates;

    @Data
    public static class Special {
        private int vipZone;   //Зона премиального обслуживания Клиенты с пакетом «Привилегия»
        private int vipOffice; //Хер знает, что это. На их карте эти не отображаются
        private int ramp;      //Доступно для маломобильных граждан
        private int person;    //обслуживание физических лиц
        private int juridical; //обслуживание юридических лиц
        @JsonProperty("Prime")
        private int prime;     //Хер знает, что это. На их карте эти не отображаются
    }

    @Data
    public static class Coordinates {
        private Double longitude;
        private Double latitude;
    }
}
