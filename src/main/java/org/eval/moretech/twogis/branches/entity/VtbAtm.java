package org.eval.moretech.twogis.branches.entity;

import lombok.Data;

@Data
public class VtbAtm {
    private String code;
    private String ownerBank;
    private String organization;
    private String city;
    private String address;
    private Double latitude;
    private Double longitude;
    private Working working;

    @Data
    public static class Working {
        private String status;
        private String from;
        private String till;
    }
}
