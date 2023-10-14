package org.eval.moretech.twogis.branches.controller;

import lombok.RequiredArgsConstructor;
import org.eval.moretech.twogis.branches.service.AtmService;
import org.eval.moretech.twogis.branches.service.BranchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/import")
@RequiredArgsConstructor
public class ImportController {

    private final BranchService branchService;
    private final AtmService atmService;

    @PostMapping("/native/branches")
    public void importNativeBranches() {
        branchService.importNative();
    }

    @PostMapping("/native/atms")
    public void importNativeAtms(Double lon, Double lat) {
        atmService.importAtms(lon, lat);
    }
}
