package com.lukepeace.projects.nevyhodcore.service;

import com.lukepeace.projects.common.vo.UserLoginVO;
import com.lukepeace.projects.common.vo.UserRegisterVO;

public interface SecurityMonitoringService {
    UserLoginVO login(UserLoginVO userLoginVO);
    UserRegisterVO register(UserRegisterVO userRegister);
}
