package org.eval.moretech.twogis.branches.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eval.moretech.twogis.branches.entity.*;
import org.eval.moretech.twogis.branches.mapper.PlaceMapper;
import org.eval.moretech.twogis.branches.mapper.PoorPlaceMapper;
import org.eval.moretech.twogis.branches.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final PoorPlaceMapper poorPlaceMapper;
    private final PlaceMapper placeMapper;

    public List<DistancedPlace> findNearestBranches(FindNearestRequest request) {
        return placeRepository.findNearest(request);
    }

    public List<PlaceDto> findNearest(FindNearestRequest request) {
        return placeMapper.map(
            findNearestBranches(request)
        );
    }

    public List<PoorPlaceDto> findPoorNearest(FindNearestRequest request) {
        return poorPlaceMapper.map(
            findNearestBranches(request)
        );
    }
}
