package com.lukepeace.projects.nevyhodcore.service;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.vo.UserLoginVO;
import com.lukepeace.projects.common.vo.UserRegisterVO;
import com.lukepeace.projects.nevyhodcore.util.UserFilter;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;

import java.util.List;

public interface UserService extends IService<UserVO, String, UserFilter> {
    UserVO create(UserVO user) throws GeneralException;
    UserVO update(UserVO user) throws GeneralException;
    void delete(String email);
    List<UserVO> findAll() throws GeneralException;
    ItemVO findById(Long id);
    UserLoginVO login(UserLoginVO userLoginVO);
    UserRegisterVO register(UserRegisterVO userRegister);

}
