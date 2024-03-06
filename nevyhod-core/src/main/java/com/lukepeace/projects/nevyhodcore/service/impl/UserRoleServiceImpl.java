package com.lukepeace.projects.nevyhodcore.service.impl;

import com.lukepeace.projects.nevyhodcore.criteria.GeneralCriteria;
import com.lukepeace.projects.nevyhodcore.entity.UserRole;
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
        AbstractServiceImpl<UserRole, UserRoleVO, UserRoleRepository, String, GeneralFilter, GeneralCriteria>
    implements UserRoleService {

    @Override
    public List<UserRoleVO> findAll() {
        List<UserRole> all = super.getRepository().findAll();
        List<UserRoleVO> vos = super.map2VO(all);
        return vos;
    }
}
