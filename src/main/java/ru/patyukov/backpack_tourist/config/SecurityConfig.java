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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().defaultSuccessUrl("/user", true);

        http.logout().logoutSuccessUrl("/home");

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/styles/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/home").permitAll()
                .mvcMatchers(HttpMethod.POST, "/home").permitAll()
                .mvcMatchers(HttpMethod.POST, "/home/reg").permitAll()
                .anyRequest().hasAnyRole("ADMIN", "USER");
    }
}
