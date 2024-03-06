package com.lukepeace.projects.nevyhodcore.service.impl;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.criteria.GeneralCriteria;
import com.lukepeace.projects.nevyhodcore.entity.UserRole;
import com.lukepeace.projects.nevyhodcore.entity.pk.UserRolePk;
import com.lukepeace.projects.nevyhodcore.repository.UserRoleRepository;
import com.lukepeace.projects.nevyhodcore.service.AbstractServiceImpl;
import com.lukepeace.projects.nevyhodcore.service.UserRoleService;
import com.lukepeace.projects.nevyhodcore.util.GeneralFilter;
import com.lukepeace.projects.nevyhodcore.vo.UserRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserRoleServiceImpl extends
        AbstractServiceImpl<UserRole, UserRoleVO, UserRoleRepository, UserRolePk, GeneralFilter, GeneralCriteria>
    implements UserRoleService {
    @Override
    public List<UserRole> findAll() {
        List<UserRole> all = super.getRepository().findAll();
        return all;
    }

    @Override
    public UserRoleVO create(UserRoleVO role) throws GeneralException {
        UserRoleVO obj = super.create(role, new UserRolePk(role.getId().getEmail(), role.getId().getName()));
        return obj;
    }
}
