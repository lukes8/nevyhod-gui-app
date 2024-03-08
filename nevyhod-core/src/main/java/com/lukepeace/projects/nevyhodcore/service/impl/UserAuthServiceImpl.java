package com.lukepeace.projects.nevyhodcore.service.impl;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.util.ValidationUtilHelper;
import com.lukepeace.projects.common.vo.UserDetailVO;
import com.lukepeace.projects.common.vo.UserLoginVO;
import com.lukepeace.projects.common.vo.UserRegisterVO;
import com.lukepeace.projects.nevyhodcore.criteria.UserCriteria;
import com.lukepeace.projects.nevyhodcore.entity.User;
import com.lukepeace.projects.nevyhodcore.repository.UserRepository;
import com.lukepeace.projects.nevyhodcore.service.UserAuthService;
import com.lukepeace.projects.nevyhodcore.util.MapperUtil;
import com.lukepeace.projects.nevyhodcore.util.UserFilter;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service @Qualifier("userAuthService")
@Slf4j
public class UserAuthServiceImpl
        extends UserServiceImpl<User, UserVO, UserRepository, String, UserFilter, UserCriteria>
        implements UserAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ValidationUtilHelper<UserRegisterVO> validationHelper4UserRegisterVO;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired private MapperUtil mapperUtil;

    @Override
    public UserDetailVO login(UserLoginVO userLogin) throws GeneralException {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(userLogin.getEmail(), userLogin.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        UserVO userVO = super.findById(userLogin.getEmail());
        UserDetailVO userDetailVO = UserDetailVO.builder().username(userVO.getEmail()).password(userVO.getPassword()).roles(mapperUtil.mapListUserRole2ListUserDetailsRole(userVO.getRoles())).isEnabled(userVO.getEnabled()).lastLogin(userVO.getLastLoginDate()).build();
        return userDetailVO;
    }

    @Override
    public UserRegisterVO register(UserRegisterVO userRegister) throws GeneralException {
        //TODO simple validate, save
        validationHelper4UserRegisterVO.validate(userRegister);

        // TODO add converter map typeMap
        UserVO user = UserVO.builder().name(userRegister.getUsername()).email(userRegister.getEmail()).createdDate(LocalDateTime.now()).build();
        user.setPassword(encoder.encode(userRegister.getPassword()));
        UserVO userVO = create(user);
        UserRegisterVO map = getModelMapper().map(userVO, UserRegisterVO.class);
        return map;
    }
}
