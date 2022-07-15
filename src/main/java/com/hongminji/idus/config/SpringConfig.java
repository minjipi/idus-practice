package com.hongminji.idus.config;

import com.hongminji.idus.jwt.JwtAccessDeniedHandler;
import com.hongminji.idus.jwt.JwtAuthenticationEntryPoint;
import com.hongminji.idus.jwt.JwtSecurityConfig;
import com.hongminji.idus.jwt.TokenProvider;
import com.hongminji.idus.member.repository.DeniedTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final DeniedTokenRepository deniedTokenRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/member/signup").permitAll()
                .antMatchers("/member/login").permitAll()
                .antMatchers("/member/refresh").permitAll()
                .antMatchers("/member/logout").hasRole("MEMBER")
                .antMatchers("/member/list").hasRole("MEMBER")
                .antMatchers("/order/create").hasRole("MEMBER")
                .antMatchers("/order/*").hasRole("MEMBER")
                .anyRequest().permitAll()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider, deniedTokenRepository));


        return http.build();
    }




}
