package ru.patyukov.backpack_tourist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.dto.HikeDto;
import ru.patyukov.backpack_tourist.entity.Hike;
import ru.patyukov.backpack_tourist.entity.User;
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
    public HikeDto addHike(HikeDto hikeDto) {

        User user = new User();
        user.setLogin(hikeDto.getUserLogin());

        Hike hike = hikeMapper.hikeDtoToHike(hikeDto);
        hike.setUser(user);

        return hikeMapper.hikeToHikeDto(hikeRepository.save(hike));
    }

    @Override
    public List<HikeDto> findByUserLogin(String login) {
        return hikeRepository.findByUserLogin(login).stream()
                .map(hike -> hikeMapper.hikeToHikeDto(hike))
                .collect(Collectors.toList());
    }

    public boolean existsById(long id, String login) {
        //TODO тут нужно сделать проверку
//        User user = userRepository.findById(login).get();
//        List<Hike> hikeList = user.getHikeList();
//        for (Hike hike : hikeList) {
//            if (hike.getId() == id) {
//                return true;
//            }
//        }
        return false;
    }

    public Hike findById(long id, String login) {
        //TODO тут нужно сделать проверку
//        User user = userRepository.findById(login).get();
//        List<Hike> hikeList = user.getHikeList();
//        for (Hike hike : hikeList) {
//            if (hike.getId() == id) {
//                return hike;
//            }
//        }
        return new Hike();
    }
}
