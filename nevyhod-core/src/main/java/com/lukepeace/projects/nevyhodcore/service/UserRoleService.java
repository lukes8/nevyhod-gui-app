package com.lukepeace.projects.nevyhodcore.service;

import com.lukepeace.projects.nevyhodcore.util.GeneralFilter;
import com.lukepeace.projects.nevyhodcore.vo.UserRoleVO;

import java.util.List;

public interface UserRoleService extends IService<UserRoleVO, String, GeneralFilter> {
    List<UserRoleVO> findAll();

}
