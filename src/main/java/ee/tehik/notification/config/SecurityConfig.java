package ee.tehik.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Basic CORS and CSRF configuration for API access
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            
            .authorizeHttpRequests(auth -> auth
                // Allow access to H2 console for debugging
                .requestMatchers("/h2-console/**").permitAll()
                // Public endpoints for fetching operational status
                .requestMatchers("/api/public/**").permitAll()
                // Secure administrative actions with role-based access
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                // Fallback for any other request
                .anyRequest().authenticated()
            )
            
            // Allow H2 console to be rendered in frames from the same origin
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
            // Enable standard Basic Authentication
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Define admin user for the technical assessment
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}tehik2026") // No-op encoder for testing purposes
                .roles("ADMIN")
                .build();
        
        return new InMemoryUserDetailsManager(admin);
    }
}