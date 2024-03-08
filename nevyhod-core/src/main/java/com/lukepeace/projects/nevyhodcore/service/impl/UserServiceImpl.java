package com.lukepeace.projects.nevyhodcore.service.impl;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.security.Permission;
import com.lukepeace.projects.common.util.PagingSortingFilter;
import com.lukepeace.projects.common.util.ValidationUtilHelper;
import com.lukepeace.projects.common.vo.UserRegisterVO;
import com.lukepeace.projects.nevyhodcore.criteria.ICriteria;
import com.lukepeace.projects.nevyhodcore.criteria.UserCriteria;
import com.lukepeace.projects.nevyhodcore.entity.User;
import com.lukepeace.projects.nevyhodcore.entity.UserRole;
import com.lukepeace.projects.nevyhodcore.repository.UserRepository;
import com.lukepeace.projects.nevyhodcore.repository.UserRoleRepository;
import com.lukepeace.projects.nevyhodcore.service.AbstractServiceImpl;
import com.lukepeace.projects.nevyhodcore.service.UserService;
import com.lukepeace.projects.nevyhodcore.util.UserFilter;
import com.lukepeace.projects.nevyhodcore.vo.UserRoleVO;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;
import com.lukepeace.projects.nevyhodcore.vo.pk.UserRolePkVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("userService")
@Slf4j
public class UserServiceImpl<E,
        VO,
        R extends JpaRepository & QuerydslPredicateExecutor,
        ID,
        F extends PagingSortingFilter,
        CR extends ICriteria>
        extends AbstractServiceImpl<User, UserVO, UserRepository, String, UserFilter, UserCriteria>
        implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    //    @Autowired
//    private SecurityMonitoringService securityMonitoringService;
    @Autowired
    private ValidationUtilHelper<UserRegisterVO> validationHelper4UserRegisterVO;

    @Override
    public List<UserVO> findAll() throws GeneralException {

        log.debug("Green debug test");
        this.validateExistence();
        List<User> lst = repository.findAll();
        List<UserVO> lstVO = this.map2VO(lst, UserVO.class);
        return lstVO;
    }

    @Override
    public UserVO findById(String email) throws GeneralException {
        super.validate(email);
        User byId = repository.findById(email).get();
        UserVO userVO = super.map2VO(byId);
        return userVO;
    }

    @Override
    public UserVO create(UserVO user) throws GeneralException {
        validateAndProcessRoles(user);

        UserVO obj = super.create(user, user.getEmail());
        saveRoles(obj.getRoles());
        return obj;
    }

    private void saveRoles(List<UserRoleVO> roles) {
        List<UserRole> lst = roles.stream().map(o -> getModelMapper().map(o, UserRole.class)).collect(Collectors.toList());
        log.debug("green lst");
        lst.forEach(o -> log.debug(o.toString()));
        if (lst.size() != 0) {
            userRoleRepository.saveAll(lst);
        }
    }

    private void validateAndProcessRoles(UserVO user) {
        user.setRoles(new ArrayList<>(user.getRoles()));
        if (user.getRoles().stream().anyMatch(o -> !o.getId().getName().equalsIgnoreCase(Permission.ROLE_USER))) {
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
