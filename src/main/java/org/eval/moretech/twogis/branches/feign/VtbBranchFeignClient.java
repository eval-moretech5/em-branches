package org.eval.moretech.twogis.branches.feign;

import org.eval.moretech.twogis.branches.entity.VtbBranchesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "VtbApiBranch", url = "${vtb.api.branch.url}")
public interface VtbBranchFeignClient {
    @GetMapping
    VtbBranchesResponse getBranches();
}
