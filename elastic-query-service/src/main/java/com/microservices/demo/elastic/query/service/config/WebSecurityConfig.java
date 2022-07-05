package com.microservices.demo.elastic.query.service.config;

import com.microservices.demo.config.UserConfigData;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserConfigData userConfigData;

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/**").permitAll();

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/**").hasRole("USER")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("test")
//                .password("{noop}test1234")
//                .roles("USER");

//        auth.inMemoryAuthentication()
//                .withUser(userConfigData.getUsername())
//                .password(passwordEncoder().encode(userConfigData.getPassword()))
//                .roles(userConfigData.getRoles());

        auth.inMemoryAuthentication()
                .withUser(userConfigData.getUsername())
                .password(userConfigData.getPassword())
                .roles(userConfigData.getRoles());
    }

//    @Bean
//    protected PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
