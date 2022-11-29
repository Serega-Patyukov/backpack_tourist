package ru.patyukov.backpack_tourist.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.dto.HikeDto;
import ru.patyukov.backpack_tourist.entity.Hike;
import ru.patyukov.backpack_tourist.entity.User;
import ru.patyukov.backpack_tourist.exception.BadRequestException;
import ru.patyukov.backpack_tourist.mapper.HikeMapper;
import ru.patyukov.backpack_tourist.repository.HikeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HikeServiceImpl implements HikeService {

    private final HikeRepository hikeRepository;
    private final HikeMapper hikeMapper;

    @Override
    public HikeDto saveHike(HikeDto hikeDto) {
        User user = new User();
        user.setLogin(hikeDto.getUserLogin());

        Hike hike = hikeMapper.hikeDtoToHike(hikeDto);
        hike.setUser(user);

        return hikeMapper.hikeToHikeDto(hikeRepository.save(hike));
    }

    @Override
    public HikeDto getHike(Long idHike) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String login = securityContext.getAuthentication().getName();

        Hike hike = hikeRepository.findById(idHike)
                .orElseThrow(() -> new BadRequestException("404"));

        if (!login.equals(hike.getUser().getLogin())) {
            throw new BadRequestException("403");
        }

        HikeDto hikeDto = hikeMapper.hikeToHikeDto(hike);
        hikeDto.setUserLogin(hike.getUser().getLogin());

        return hikeDto;
    }

    @Override
    public List<HikeDto> findByUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String login = securityContext.getAuthentication().getName();

        return hikeRepository.findByUserLogin(login).stream()
                .map(hike -> hikeMapper.hikeToHikeDto(hike))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHike(Long idHike) {
        hikeRepository.deleteById(idHike);
    }
}
