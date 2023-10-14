package org.eval.moretech.twogis.branches.controller;

import lombok.RequiredArgsConstructor;
import org.eval.moretech.twogis.branches.entity.BranchScheduleDto;
import org.eval.moretech.twogis.branches.entity.FindNearestRequest;
import org.eval.moretech.twogis.branches.entity.PlaceDto;
import org.eval.moretech.twogis.branches.entity.PoorPlaceDto;
import org.eval.moretech.twogis.branches.service.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public List<PlaceDto> findNearest(@RequestBody FindNearestRequest request) {
        List<PlaceDto> places = placeService.findNearest(request);
        return places;
    }

    @PostMapping("/poor")
    public List<PoorPlaceDto> findPoorNearest(@RequestBody FindNearestRequest request) {
        List<PoorPlaceDto> places = placeService.findPoorNearest(request);
        return places;
    }

    @GetMapping("/branches/schedules")
    public List<BranchScheduleDto> getAllBranches() {
        return placeService.getAllBranchesPersonTypesAndSchedules();
    }

    @PostMapping("/branches/schedules/filtered")
    public List<BranchScheduleDto> getAllBranchesFiltered(@RequestBody FindNearestRequest request) {
        return placeService.getAllBranchesPersonTypesAndSchedules(request);
    }
}
