package ru.patyukov.backpack_tourist.service;

import ru.patyukov.backpack_tourist.dto.HikeDto;
import ru.patyukov.backpack_tourist.entity.Hike;

import java.util.List;

public interface HikeService {
    HikeDto addHike(HikeDto hikeDto);
    List<HikeDto> findByUserLogin(String login);
    HikeDto findById(Long id);
}
