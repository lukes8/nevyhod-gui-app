package com.lukepeace.projects.nevyhodcore.service.impl;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.security.Permission;
import com.lukepeace.projects.common.vo.UserLoginVO;
import com.lukepeace.projects.common.vo.UserRegisterVO;
import com.lukepeace.projects.nevyhodcore.criteria.UserCriteria;
import com.lukepeace.projects.nevyhodcore.entity.User;
import com.lukepeace.projects.nevyhodcore.entity.UserRole;
import com.lukepeace.projects.nevyhodcore.repository.UserRepository;
import com.lukepeace.projects.nevyhodcore.repository.UserRoleRepository;
import com.lukepeace.projects.nevyhodcore.service.AbstractServiceImpl;
import com.lukepeace.projects.nevyhodcore.service.UserService;
import com.lukepeace.projects.nevyhodcore.util.UserFilter;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import com.lukepeace.projects.nevyhodcore.vo.UserRoleVO;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;
import com.lukepeace.projects.nevyhodcore.vo.pk.UserRolePkVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl extends
        AbstractServiceImpl<User, UserVO, UserRepository, String, UserFilter, UserCriteria>
    implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired private UserRoleRepository userRoleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public List<UserVO> findAll() throws GeneralException {

        log.debug("Green debug test");
        this.validateExistence();
        List<User> lst = repository.findAll();
        List<UserVO> lstVO = this.map2VO(lst, UserVO.class);
        return lstVO;
    }

    @Override
    public ItemVO findById(Long id) {
        return null;
    }

    @Override
    public UserLoginVO login(UserLoginVO userLogin) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(userLogin.getEmail(), userLogin.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        return userLogin;
    }

    @Override
    public UserRegisterVO register(UserRegisterVO userRegister) {
        //TODO simple validate, save
        return userRegister;
    }

    @Override
    public UserVO create(UserVO user) throws GeneralException {
        validateAndProcessVO(user);

        UserVO obj = super.create(user, user.getEmail());
        saveRoles(obj.getRoles());
        return obj;
    }

    private void saveRoles(List<UserRoleVO> roles) {
        List<UserRole> lst = roles.stream().map(o -> getModelMapper().map(o, UserRole.class)).collect(Collectors.toList());
        log.debug("green lst");
        lst.forEach(o -> log.debug(o.toString()));
        userRoleRepository.saveAll(lst);
    }

    private void validateAndProcessVO(UserVO user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(new ArrayList<>());
            user.getRoles().add(UserRoleVO.builder().id(UserRolePkVO.builder().name(Permission.ROLE_USER).build()).build());
        }
        user.getRoles().forEach(o -> o.getId().setEmail(user.getEmail()));
    }

    @Override
    public UserVO update(UserVO user) throws GeneralException {
        super.validateBeforeCreate(user.getEmail());
        return user;
    }

    @Override
    public void delete(String email) {
        //TODO
    }
}
