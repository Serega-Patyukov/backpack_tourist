package ru.patyukov.backpack_tourist.service;

import ru.patyukov.backpack_tourist.dto.HikeDto;

import java.util.List;

public interface HikeService {
    HikeDto saveHike(HikeDto hikeDto);
    List<HikeDto> findByUserLogin(String login);
    HikeDto getHike(Long idHike);
    void deleteHike(Long idHike);
}

