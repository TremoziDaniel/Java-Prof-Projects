package com.bank.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
// Mvc matchers
// https://stackoverflow.com/questions/72194656/basic-auth-spring-security-with-enum-roles-and-permissions-always-return-401
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll()
                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/clients").permitAll()
                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/personalData").permitAll()
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/currencies/").permitAll()
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/currencies/{\\d+}").permitAll()
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/currencies/abb/{\\[a-zA-Z]+}").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
