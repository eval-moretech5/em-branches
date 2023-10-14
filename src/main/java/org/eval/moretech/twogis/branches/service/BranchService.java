package org.eval.moretech.twogis.branches.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eval.moretech.twogis.branches.entity.Place;
import org.eval.moretech.twogis.branches.entity.ServiceType;
import org.eval.moretech.twogis.branches.entity.VtbBranchesResponse;
import org.eval.moretech.twogis.branches.feign.VtbBranchFeignClient;
import org.eval.moretech.twogis.branches.mapper.BranchMapper;
import org.eval.moretech.twogis.branches.repository.PlaceRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class BranchService {

    private static final int MAX_LINE_TIME_IN_SECONDS = 40 * 60;

    private final Random random = new Random();
    private final PlaceRepository placeRepository;
    private final VtbBranchFeignClient branchFeignClient;
    private final BranchMapper branchMapper;

    @Async
    @Transactional
    public void importNative() {

        VtbBranchesResponse response = branchFeignClient.getBranches();

        log.info("Fetched {} branches", response.getBranches().size());

        response.getBranches().forEach(b -> {
            Place place = branchMapper.map(b);
            place.setLineTime(random.nextInt(0, MAX_LINE_TIME_IN_SECONDS));
            place.setServices(generateServices());
            placeRepository.save(place);
            log.debug("Branch {} saved", place);
        });

        log.info("Branches are imported");

    }

    private Set<ServiceType> generateServices() {
        Set<ServiceType> services = new HashSet<>(ServiceType.DEFAULT);
        for (ServiceType service : ServiceType.NOT_DEFAULT) {
            if (random.nextBoolean()) {
                services.add(service);
            }
        }
        return services;
    }
}
