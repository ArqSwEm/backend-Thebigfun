package com.thebigfun.user_service.config;import lombok.RequiredArgsConstructor;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import org.springframework.http.HttpMethod;import org.springframework.security.authentication.AuthenticationProvider;import org.springframework.security.config.annotation.web.builders.HttpSecurity;import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;import org.springframework.security.config.http.SessionCreationPolicy;import org.springframework.security.web.SecurityFilterChain;import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;@Configuration@EnableWebSecurity@RequiredArgsConstructorpublic class SecurityConfig {    private final JwtAuthenticationFilter jwtAuthFilter;    private final AuthenticationProvider authenticationProvider;    @Bean    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {        http.cors().and()                .authorizeHttpRequests(authorize -> authorize                        .requestMatchers("/api/thebigfun/v1/auth/**",                                "/api/thebigfun/v1/users/**","/api/thebigfun/v1/users/{userId}/events","/api/user_services/users/{userId}").permitAll()                        .requestMatchers(HttpMethod.GET,"/api/thebigfun/v1/userSs/**").hasAnyAuthority("ADMIN")                        .requestMatchers("/api/thebigfun/v1/userss",                                "/api/thebigfun/v1/users/{userId}/addImage",                                "/api/betabyte/v1/rents",                                "/api/betabyte/v1/cards",                                "/api/betabyte/v1/bicycles/**").authenticated()                        .anyRequest().authenticated())                .csrf(csrf -> csrf.disable())                .sessionManagement(session -> session                        // cuando se establece en STATELESS, significa que no se creará ni                        // mantendrá ninguna sesión HTTP en el servidor.                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))                .authenticationProvider(authenticationProvider)                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);        return http.build();    }}