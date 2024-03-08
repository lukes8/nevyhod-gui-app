package com.lukepeace.projects.common.security;

import com.lukepeace.projects.common.util.GlobalConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@Slf4j
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    private Environment environment;
    @Autowired
    private GlobalConfiguration globalConfig;
    @Bean
    public SecurityFilterChain securityFilterChain(@Value("${spring.security.whitelist}") String[] whitelist, HttpSecurity http) throws Exception {

        log.info("whitelist: " + Arrays.stream(whitelist).collect(Collectors.joining()));
        log.info("green: " + globalConfig.getIsDev() + " " + globalConfig.getProfileName());

        if (globalConfig.getIsDev()) {
            http
                    .cors(Customizer.withDefaults())
                    .csrf(csrf -> csrf.disable())
                    .headers(header -> header.frameOptions(f -> f.disable()))//h2-console in frame
                    .authorizeHttpRequests((authorize) -> authorize
                                    .requestMatchers("swagger-ui/**",
                                            "/rest/api/user/login",
                                            "/rest/api/user/register",
                                            "/rest/api/test", "h2-console/**",
                                            "resources/**")
                                    .permitAll()
//                            .requestMatchers(whitelist).permitAll()
                                    .anyRequest().authenticated()
                    )
                    .httpBasic(Customizer.withDefaults())
                    .formLogin(Customizer.withDefaults());
            //TODO filter + sessionstateless

        } else {
            http
                    .cors(Customizer.withDefaults())
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers(whitelist).permitAll()
                            .anyRequest()
                            .authenticated()
                    )
                    .httpBasic(Customizer.withDefaults())
                    .formLogin(Customizer.withDefaults());
        }

        return http.build();
    }
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/rest/api/*").allowedOrigins("http://localhost:8888");
            }
        };
    }
    @Bean
    public PasswordEncoder bcryptEncoder() {
        return new BCryptPasswordEncoder();
    }
}