package com.lukepeace.projects.nevyhodcore.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.criteria.UserCriteria;
import com.lukepeace.projects.nevyhodcore.entity.User;
import com.lukepeace.projects.nevyhodcore.repository.UserRepository;
import com.lukepeace.projects.nevyhodcore.service.AbstractServiceImpl;
import com.lukepeace.projects.nevyhodcore.service.UserService;
import com.lukepeace.projects.nevyhodcore.util.UserFilter;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends
        AbstractServiceImpl<User, UserVO, UserRepository, String, UserFilter, UserCriteria>
    implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    public static final List<UserVO> dummyList() {

        List<UserVO> lst = Arrays.asList(
                UserVO.builder().email("luke@green.com").createdDate(LocalDateTime.now()).name("luke").enabled(true).build(),
                UserVO.builder().email("tim@green.com").createdDate(LocalDateTime.now()).name("tim").enabled(true).build()
        );
        return lst;
    }

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
    public UserVO create(UserVO user) throws GeneralException {
        UserVO obj = super.create(user, user.getEmail());
        return obj;
    }

    @Override
    public UserVO update(UserVO user) throws GeneralException {
        super.validateBeforeCreate(user.getEmail());
        return user;
    }

    @Override
    public UserVO delete(String email) {
        return null;
    }
}
