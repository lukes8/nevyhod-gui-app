package com.lukepeace.projects.common.security;

import com.lukepeace.projects.common.util.GlobalConfiguration;
import com.lukepeace.projects.common.vo.UserRoleVO;
import com.lukepeace.projects.common.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Value("${test.user.username:luke}")
    private String testUser;
    @Value("${test.user.password:noop}")
    private String testPassword;

    @Autowired
    private Environment environment;

    @Autowired
    private GlobalConfiguration configuration;

    @Bean
    public SecurityFilterChain securityFilterChain(@Value("${spring.security.whitelist}") String[] whitelist, HttpSecurity http) throws Exception {

        log.info("whitelist: " + Arrays.stream(whitelist).collect(Collectors.joining()));
        log.info("green: " + configuration.getIsDev() + " " + configuration.getProfileName());

        if (configuration.getIsDev()) {
            http
                    .cors(Customizer.withDefaults())
                    .csrf(csrf -> csrf.disable() )
                    .authorizeHttpRequests((authorize) -> authorize
                            .anyRequest().permitAll()
                    );
        } else {
            http
                    .cors(Customizer.withDefaults())
                    .csrf(csrf -> csrf.disable() )
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
    public CorsConfigurationSource corsConfigurationSource() {
        // TODO check
        log.info("CORS defined green");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return cn -> {
            UserVO user = null;
            List<UserRoleVO> roles = new ArrayList<>();
            if (Arrays.asList(environment.getActiveProfiles()).contains("DEV_PROFILE")) {
                log.info("security dev profile");
                roles.add(UserRoleVO.builder().role("ADMIN").build());
                roles.add(UserRoleVO.builder().role("USER").build());
                roles.add(UserRoleVO.builder().role("USER_MAINTENANCE").build());
                user = UserVO.builder().username(testUser).password("{noop}" + testPassword).roles(roles).isEnabled(true).lastLogin(LocalDateTime.now()).build();
            } else {
                //TODO get user from database for prod env
                // TODO get user from database based on cn username
            }

            log.info("LOGIN USER");
            MDC.put("mdcUser", cn);

            log.info("LOGIN USER");

            CurrentUser currentUser = new CurrentUser(user.getPassword(), user.getUsername(), user.isEnabled(), getAuthorities(user));
            return currentUser;
        };
    }

    private List<GrantedAuthority> getAuthorities(UserVO userVO) {
        List<GrantedAuthority> authorities = userVO.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole())).collect(Collectors.toList());
        return authorities;
    }

}