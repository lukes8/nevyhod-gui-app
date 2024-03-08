package com.lukepeace.projects.nevyhodcore.service;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.vo.UserDetailVO;
import com.lukepeace.projects.common.vo.UserLoginVO;
import com.lukepeace.projects.common.vo.UserRegisterVO;

public interface UserAuthService extends UserService {
    UserDetailVO login(UserLoginVO userLoginVO) throws GeneralException;
    UserRegisterVO register(UserRegisterVO userRegister) throws GeneralException;

}
