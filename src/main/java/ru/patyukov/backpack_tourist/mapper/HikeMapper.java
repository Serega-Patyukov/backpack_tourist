package ru.patyukov.backpack_tourist.mapper;

import org.mapstruct.Mapper;
import ru.patyukov.backpack_tourist.dto.HikeDto;
import ru.patyukov.backpack_tourist.entity.Hike;
import ru.patyukov.backpack_tourist.web.request.HikeRequest;
import ru.patyukov.backpack_tourist.web.response.HikeResponse;

@Mapper(componentModel = "spring")
public interface HikeMapper {
    HikeResponse hikeDtoToHikeResponse(HikeDto hikeDto);
    HikeRequest hikeDtoToHikeRequest(HikeDto hikeDto);
    HikeDto hikeRequestToHikeDto(HikeRequest hikeRequest);
    HikeDto hikeToHikeDto(Hike hike);
    Hike hikeDtoToHike(HikeDto hikeDto);
}
