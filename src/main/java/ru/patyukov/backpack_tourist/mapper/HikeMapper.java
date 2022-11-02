package ru.patyukov.backpack_tourist.mapper;

import org.mapstruct.Mapper;
import ru.patyukov.backpack_tourist.dto.HikeDto;
import ru.patyukov.backpack_tourist.entity.Hike;
import ru.patyukov.backpack_tourist.web.request.HikeRequest;
import ru.patyukov.backpack_tourist.web.response.HikeResponse;

@Mapper(componentModel = "spring")
public interface HikeMapper {
    Hike hikeDtoToHike(HikeDto hikeDto);
    HikeDto hikeToHikeDto(Hike hike);
    HikeDto hikeRequestToHikeDto(HikeRequest hikeRequest);
    HikeResponse hikeDtoToHikeResponse(HikeDto hikeDto);
    HikeRequest hikeDtoToHikeRequest(HikeDto hikeDto);
}
