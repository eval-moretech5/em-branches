package org.eval.moretech.twogis.branches.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "places")
@Data
public class PlacesRequestProperties {
    private Integer radius;
    private Integer limit;
}
