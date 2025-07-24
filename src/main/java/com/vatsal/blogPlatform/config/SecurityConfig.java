package com.vatsal.blogPlatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity // Enables Spring Security's web security features
public class SecurityConfig {

   
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // The userDetailsService bean (for default "user" login) is intentionally NOT present here.
    // This means Spring Security will not generate a random password in the console,
    // and all non-/api/** paths will lead to a 403 Forbidden if accessed without authentication.
}
