package com.lukepeace.projects.common.security;

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
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
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

    @Bean
    public SecurityFilterChain securityFilterChain(@Value("${spring.security.whitelist}") String[] whitelist, HttpSecurity http) throws Exception {

        log.info("whitelist: " + Arrays.stream(whitelist).collect(Collectors.joining()));
        http
                .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers(whitelist)
                            .permitAll()
                            .anyRequest()
                            .authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
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