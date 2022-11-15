package ru.patyukov.backpack_tourist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();

        UserDetails user = User.withUsername("admin")
                .password("$2a$12$t7b04Sdu.Ow9Ljw8YMwXw.6zw2oHsHQXYZS5MGhkIysGlZCsobsCW")
                .roles("ADMIN")
                .build();

        userDetailsService.createUser(user);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/styles/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/home").permitAll()
                .mvcMatchers(HttpMethod.POST, "/home").permitAll()
                .mvcMatchers(HttpMethod.POST, "/home/reg").permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();
    }
}
