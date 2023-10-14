package org.eval.moretech.twogis.branches.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "vtb.api")
@Data
public class VtbApiProperties {

    private Atm atm;

    @Data
    public static class Atm {
        private String referer;
        private Integer radius;
    }
}
