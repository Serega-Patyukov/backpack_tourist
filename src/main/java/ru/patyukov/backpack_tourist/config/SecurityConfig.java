package ru.patyukov.backpack_tourist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.patyukov.backpack_tourist.web.constant.WebConstant;
import ru.patyukov.backpack_tourist.web.filter.InitialAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private InitialAuthenticationFilter initialAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class);

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/styles/**").permitAll()
                .mvcMatchers(HttpMethod.GET, WebConstant.VERSION_URL + "/home").permitAll()
                .mvcMatchers(HttpMethod.POST, WebConstant.VERSION_URL + "/home").permitAll()
                .mvcMatchers().hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated();

    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
