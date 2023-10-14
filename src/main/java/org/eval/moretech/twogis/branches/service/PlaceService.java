package org.eval.moretech.twogis.branches.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eval.moretech.twogis.branches.entity.*;
import org.eval.moretech.twogis.branches.mapper.BranchMapper;
import org.eval.moretech.twogis.branches.mapper.PlaceMapper;
import org.eval.moretech.twogis.branches.mapper.PoorPlaceMapper;
import org.eval.moretech.twogis.branches.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final PoorPlaceMapper poorPlaceMapper;
    private final PlaceMapper placeMapper;
    private final BranchMapper branchMapper;

    @Transactional
    public List<BranchScheduleDto> getAllBranchesPersonTypesAndSchedules() {
        return placeRepository.getPlacesByType(Place.PlaceType.BRANCH)
            .stream()
            .map(branchMapper::map)
            .toList();
    }

    @Transactional
    public List<BranchScheduleDto> getAllBranchesPersonTypesAndSchedules(FindNearestRequest request) {
        return placeRepository.findNearest(request)
            .stream()
            .filter(p -> Place.PlaceType.BRANCH.equals(p.getType()))
            .map(branchMapper::mapScheduled)
            .toList();
    }

    private List<DistancedPlace> findNearestBranches(FindNearestRequest request) {
        return placeRepository.findNearest(request);
    }

    @Transactional
    public List<PlaceDto> findNearest(FindNearestRequest request) {
        return placeMapper.map(
            findNearestBranches(request)
        );
    }

    @Transactional
    public List<PoorPlaceDto> findPoorNearest(FindNearestRequest request) {
        return poorPlaceMapper.map(
            findNearestBranches(request)
        );
    }
}
