package mindswap.academy.springorders.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static mindswap.academy.springorders.auth.model.Role.ADMIN;
import static mindswap.academy.springorders.auth.model.Role.MANAGER;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Autowired
    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthFilter,
            AuthenticationProvider authenticationProvider,
            LogoutHandler logoutHandler
    ) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
        this.logoutHandler = logoutHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(
                                "/api/v1/auth/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html",
                                "/api/**"
                        ).permitAll())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                )
                //.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                //.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout
                                .addLogoutHandler(logoutHandler)
                                .logoutUrl("/api/v1/auth/logout")
                                .logoutSuccessHandler(
                                        (request, response, authentication) ->
                                                SecurityContextHolder.clearContext()
                                )
                );
*/
        return http.build();
    }
}
