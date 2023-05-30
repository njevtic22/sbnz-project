package com.ftn.sbnz.service.config;

import com.ftn.sbnz.service.security.TokenUtils;
import com.ftn.sbnz.service.security.rest.RestAuthenticationEntryPoint;
import com.ftn.sbnz.service.security.rest.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
    private final TokenUtils tokenUtils;
    private final UserDetailsService userDetailsService;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    public WebSecurityConfiguration(TokenUtils tokenUtils, UserDetailsService userDetailsService, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userDetailsService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

                .authorizeHttpRequests().antMatchers(HttpMethod.POST, "/api/auth/login").permitAll().and()
                .authorizeHttpRequests().antMatchers(HttpMethod.POST, "/api/examples/execute-example").permitAll().and()
                .authorizeHttpRequests().antMatchers(HttpMethod.POST, "/api/examples/first-level-example/{id}").permitAll().and()
                .authorizeHttpRequests().antMatchers(HttpMethod.POST, "/api/examples/second-level-example/{id}").permitAll().and()
                .authorizeHttpRequests().antMatchers(HttpMethod.GET,
                        "/", "/webjars/**", "/*.html", "/favicon.ico", "/*/*.html",
                        "/*/*.css", "/*/*.js").permitAll().and()
                .authorizeHttpRequests().anyRequest().authenticated().and()

                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, userDetailsService), BasicAuthenticationFilter.class);

        http.cors();
        http.csrf().disable();

        return http.build();
    }
}
