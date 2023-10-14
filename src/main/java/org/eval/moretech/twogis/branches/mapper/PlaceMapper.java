package org.eval.moretech.twogis.branches.mapper;

import lombok.RequiredArgsConstructor;
import org.eval.moretech.twogis.branches.entity.DistancedPlace;
import org.eval.moretech.twogis.branches.entity.PlaceDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaceMapper {

    private final BranchMapper branchMapper;
    private final AtmMapper atmMapper;

    public PlaceDto map(DistancedPlace source) {
        return switch (source.getType()) {
            case BRANCH -> branchMapper.map(source);
            case ATM -> atmMapper.map(source);
        };
    }

    public List<PlaceDto> map(List<DistancedPlace> source) {
        return source.stream().map(this::map).toList();
    }
}
