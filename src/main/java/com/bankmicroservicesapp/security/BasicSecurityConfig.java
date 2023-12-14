package com.bankmicroservicesapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig implements HandlerInterceptor {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        (request) ->
                                request
                                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                                        .requestMatchers(
                                                "/",
                                                "/home",
                                                "/login"
                                        )
                                        .permitAll()
                                        .requestMatchers("/swagger")
                                        .hasRole("ADMIN")
                                        .anyRequest().authenticated()

                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .logout(LogoutConfigurer::permitAll)
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling((ex) -> ex.accessDeniedPage("/logout"));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build(),
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("ADMIN")
                        .build()
        );
    }
}
