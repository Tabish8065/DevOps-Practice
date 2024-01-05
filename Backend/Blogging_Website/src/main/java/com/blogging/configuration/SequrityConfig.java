package com.blogging.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blogging.security.JwtAuthenticationEntry;
import com.blogging.security.JwtAuthenticationFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@EnableMethodSecurity
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class SequrityConfig {

    // private UserDetailsService userDetailsService;
    private JwtAuthenticationEntry jwtAuthenticationEntry;
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    public SequrityConfig( JwtAuthenticationEntry jwtAuthenticationEntry
                            ,JwtAuthenticationFilter jwtAuthenticationFilter) {//UserDetailsService userDetailsService,
        // this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntry = jwtAuthenticationEntry;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> 
                authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated())
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(jwtAuthenticationEntry)
            ).sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
