package org.eval.moretech.twogis.branches.controller;

import lombok.RequiredArgsConstructor;
import org.eval.moretech.twogis.branches.config.AtmBankProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/atm-banks")
@RequiredArgsConstructor
public class AtmBankController {

    private final AtmBankProperties atmBankProperties;

    @GetMapping
    public Map<String, String> getAtmBankProperties() {
        return atmBankProperties;
    }
}
