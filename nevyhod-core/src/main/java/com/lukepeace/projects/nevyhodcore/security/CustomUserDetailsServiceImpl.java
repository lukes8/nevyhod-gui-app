package com.lukepeace.projects.nevyhodcore.security;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.security.CurrentUser;
import com.lukepeace.projects.common.util.GlobalConfiguration;
import com.lukepeace.projects.common.vo.UserDetailVO;
import com.lukepeace.projects.common.vo.UserDetailsRoleVO;
import com.lukepeace.projects.nevyhodcore.service.UserService;
import com.lukepeace.projects.nevyhodcore.vo.UserRoleVO;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service @Slf4j
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Autowired private GlobalConfiguration globalConfiguration;
    @Autowired private PasswordEncoder encoder;
    @Autowired private ModelMapper modelMapper;
    private UserService userService;
    @Autowired
    public CustomUserDetailsServiceImpl(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserDetailVO userDetailVO = null;
            List<UserDetailsRoleVO> roles = new ArrayList<>();
            if (!globalConfiguration.getIsDev()) {
                log.info("security dev profile");

                roles.add(UserDetailsRoleVO.builder().name("ADMIN").build());
                roles.add(UserDetailsRoleVO.builder().name("USER").build());
                roles.add(UserDetailsRoleVO.builder().name("USER_MAINTENANCE").build());
                userDetailVO = UserDetailVO.builder().username(globalConfiguration.getTestUser()).password(encoder.encode(globalConfiguration.getTestPassword())).roles(roles).isEnabled(true).lastLogin(LocalDateTime.now()).build();
            } else {
                log.info("security prod profile");
                try {
                    UserVO userVO = userService.findById(username);
                    userDetailVO = UserDetailVO.builder().username(userVO.getEmail()).password(userVO.getPassword()).roles(getUserDetailsRoles(userVO.getRoles())).isEnabled(userVO.getEnabled()).lastLogin(userVO.getLastLoginDate()).build();
                } catch (GeneralException e) {
                    e.printStackTrace();
                    throw new BadCredentialsException(e.getMessage());
                }
            }

            log.info("LOGIN USER GREEN");
            MDC.put("mdcUser", username);

            log.info("LOGIN USER");

            CurrentUser currentUser = new CurrentUser(userDetailVO.getPassword(), userDetailVO.getUsername(), userDetailVO.isEnabled(), getAuthorities(userDetailVO));
            return currentUser;
    }

    private List<UserDetailsRoleVO> getUserDetailsRoles(List<UserRoleVO> roles) {
        return roles.stream().map(o -> modelMapper.map(UserRoleVO.class, UserDetailsRoleVO.class)).collect(Collectors.toList());
    }

    private List<GrantedAuthority> getAuthorities(UserDetailVO userVO) {
        List<GrantedAuthority> authorities = userVO.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName())).collect(Collectors.toList());
        return authorities;
    }
}
