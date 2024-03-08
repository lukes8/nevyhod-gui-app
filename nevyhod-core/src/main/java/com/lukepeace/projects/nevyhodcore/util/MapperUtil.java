package com.lukepeace.projects.nevyhodcore.util;

import com.lukepeace.projects.common.vo.UserDetailsRoleVO;
import com.lukepeace.projects.nevyhodcore.vo.UserRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component @Slf4j
public class MapperUtil {
    public List<UserDetailsRoleVO> getUserDetailsRoles(List<UserRoleVO> roles) {
        return roles.stream().map(o -> UserDetailsRoleVO.builder().name(o.getId().getName()).build()).collect(Collectors.toList());
    }
}
