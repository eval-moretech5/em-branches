package org.eval.moretech.twogis.branches.service;

import lombok.AllArgsConstructor;
import org.eval.moretech.twogis.branches.config.VtbApiProperties;
import org.eval.moretech.twogis.branches.entity.Place;
import org.eval.moretech.twogis.branches.entity.ServiceType;
import org.eval.moretech.twogis.branches.entity.VtbAtmsResponse;
import org.eval.moretech.twogis.branches.feign.VtbAtmFeignClient;
import org.eval.moretech.twogis.branches.mapper.AtmMapper;
import org.eval.moretech.twogis.branches.repository.PlaceRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AtmService {

    private final VtbAtmFeignClient atmFeignClient;
    private final AtmMapper atmMapper;
    private final PlaceRepository placeRepository;

    private final VtbApiProperties properties;

    @Async
    @Transactional
    public void importAtms(Double lon, Double lat) {
        VtbAtmsResponse response = atmFeignClient.getAtms(
            lon, lat,
            properties.getAtm().getReferer(),
            properties.getAtm().getRadius()
        );
        List<Place> atms = atmMapper.map(response.getAtms());
        atms.forEach(a -> {
                a.setLineTime(0);
                a.setServices(ServiceType.DEFAULT);
            });
        placeRepository.saveAll(atms);
    }
}
