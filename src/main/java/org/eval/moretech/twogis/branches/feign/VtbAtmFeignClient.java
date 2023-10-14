package org.eval.moretech.twogis.branches.feign;

import org.eval.moretech.twogis.branches.entity.VtbAtmsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "VtbApiAtm", url = "${vtb.api.atm.url}")
public interface VtbAtmFeignClient {

    @GetMapping
    VtbAtmsResponse getAtms(@RequestParam Double longitude,
                            @RequestParam Double latitude,
                            @RequestHeader(name = "referer") String referer,
                            @RequestParam Integer radius);
}
