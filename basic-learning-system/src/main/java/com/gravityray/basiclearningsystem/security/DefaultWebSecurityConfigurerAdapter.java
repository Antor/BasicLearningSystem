package com.gravityray.basiclearningsystem.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
public class DefaultWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final DefaultUserDetailsService userDetailsService;

    protected DefaultWebSecurityConfigurerAdapter(
            DefaultUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
                //.passwordEncoder(new BCryptPasswordEncoder(10));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .anyRequest().permitAll() // DEBUG
//                    .antMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
//                    .antMatchers("/api/v1/**").authenticated()
                    .and()
                .httpBasic()
                    .realmName("Basic Learning System")
                    .and()
                .csrf()
                    .disable();
    }
}
