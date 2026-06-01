package com.salesianostriana.dam.fitcenterbooking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.authorizeHttpRequests(
                (authz) -> authz
                	.requestMatchers("/", "/login", "/registro", "/css/**", "/js/**", "/img/**", "/error/**").permitAll()
                	.requestMatchers("/actividad/**", "/carrito/**", "/misReservas/**", "/reserva/ticket/**").authenticated() 
                	.requestMatchers("/reservas/**", "/usuarios/**", "/actividades/**", "/estadisticas/**").hasRole("ADMIN")
                    .anyRequest().permitAll() 
                )
                .requestCache(cache -> {
                     HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
                     requestCache.setMatchingRequestParameterName(null);
                     cache.requestCache(requestCache);
                })
                .formLogin(form -> form
                        .loginPage("/login") 
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                );

        http.csrf((csrf) -> {
            csrf.ignoringRequestMatchers("/h2/**");
        });
        http.headers((headers) -> headers.frameOptions((opts) -> opts.disable()));

        return http.build();
    }
}