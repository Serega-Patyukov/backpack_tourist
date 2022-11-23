package ru.patyukov.backpack_tourist.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.entity.User;
import ru.patyukov.backpack_tourist.model.CustomUserDetails;
import ru.patyukov.backpack_tourist.repository.UserRepository;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication!");
        User user = userRepository.findById(username).orElseThrow(s);
        return new CustomUserDetails(user);
    }
}
