package com.lukepeace.projects.nevyhodcore.service.impl;

import com.lukepeace.projects.common.vo.UserLoginVO;
import com.lukepeace.projects.common.vo.UserRegisterVO;
import com.lukepeace.projects.nevyhodcore.service.SecurityMonitoringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SecurityMonitoringServiceImpl implements SecurityMonitoringService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public UserLoginVO login(UserLoginVO userLogin) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(userLogin.getEmail(), userLogin.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        return userLogin;
    }
    @Override
    public UserRegisterVO register(UserRegisterVO userRegister) {

        return null;
    }
}
