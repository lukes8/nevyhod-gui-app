package com.lukepeace.projects.nevyhodcore.service;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.util.UserFilter;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;

import java.util.List;

public interface UserService extends IService<UserVO, String, UserFilter> {
    UserVO create(UserVO user) throws GeneralException;
    UserVO update(UserVO user) throws GeneralException;
    void delete(String email);
    List<UserVO> findAll() throws GeneralException;
    UserVO findById(String email) throws GeneralException;

}
