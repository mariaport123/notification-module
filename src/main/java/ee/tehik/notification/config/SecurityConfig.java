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
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Enable access to H2 Console
                        .requestMatchers("/h2-console/**").permitAll()
                        // Allow public access to notification endpoints
                        .requestMatchers("/api/public/**").permitAll()
                        // Secure all admin-related endpoints
                        .requestMatchers("/api/admin/**").authenticated()
                        .anyRequest().permitAll())
                // Required to display H2 Console in a browser frame
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.builder()
                .passwordEncoder(password -> "{noop}" + password) 
                .username("admin")
                .password("tehik2026")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}