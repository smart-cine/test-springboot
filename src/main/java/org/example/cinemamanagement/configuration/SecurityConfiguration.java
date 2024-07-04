package org.example.cinemamanagement.configuration;

import lombok.RequiredArgsConstructor;
import org.example.cinemamanagement.common.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/v1/user/**").permitAll()
                        .requestMatchers("/test/**").permitAll()
                        .requestMatchers("/api/v1/managers/**").hasAuthority(Role.MANAGER_ADMIN.name())
                        .requestMatchers("/api/owner/**").hasAuthority(Role.OWNER.name())
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .cors(cors -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("https://example.com")); // Specify your allowed origins
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Specify your allowed methods
                    corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With")); // Specify your allowed headers
                    corsConfiguration.setAllowCredentials(true); // Set to true if your frontend needs to send credentials (cookies, HTTP authentication)
                    corsConfiguration.addAllowedOriginPattern("*"); // Use this carefully; consider specifying patterns if needed
                    cors.configurationSource(request -> corsConfiguration);
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

