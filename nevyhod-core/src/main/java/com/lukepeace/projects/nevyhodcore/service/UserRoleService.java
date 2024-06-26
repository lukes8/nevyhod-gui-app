package com.lukepeace.projects.nevyhodcore.service;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.entity.UserRole;
import com.lukepeace.projects.nevyhodcore.entity.pk.UserRolePk;
import com.lukepeace.projects.nevyhodcore.util.GeneralFilter;
import com.lukepeace.projects.nevyhodcore.vo.UserRoleVO;

import java.util.List;

public interface UserRoleService extends IService<UserRoleVO, UserRolePk, GeneralFilter> {
    List<UserRole> findAll();
    UserRoleVO create(UserRoleVO role) throws GeneralException;

}
