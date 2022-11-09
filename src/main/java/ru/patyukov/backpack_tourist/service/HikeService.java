package ru.patyukov.backpack_tourist.service;

import ru.patyukov.backpack_tourist.dto.HikeDto;

import java.util.List;

public interface HikeService {
    HikeDto saveHike(HikeDto hikeDto);
    HikeDto getHike(Long idHike);
    List<HikeDto> findByUserLogin(String login);
    void deleteHike(Long idHike);
}

