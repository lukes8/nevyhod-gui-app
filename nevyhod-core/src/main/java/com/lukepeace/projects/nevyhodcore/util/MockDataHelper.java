package com.lukepeace.projects.nevyhodcore.util;

import com.lukepeace.projects.nevyhodcore.entity.UserRole;
import com.lukepeace.projects.nevyhodcore.entity.pk.UserRolePk;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MockDataHelper {
    public static List<UserVO> dummyList() {

        List<UserVO> lst = Arrays.asList(
                UserVO.builder().email("luke@green.com").createdDate(LocalDateTime.now()).name("luke")
                        .enabled(true)
                        .roles(Arrays.asList(UserRole.builder()
                                .id(UserRolePk.builder()
                                        .email("luke@green.com")
                                        .name("green name").build()).build()) )
                        .build(),
                UserVO.builder().email("tim@green.com").createdDate(LocalDateTime.now()).name("tim").enabled(true).build()
        );
        return lst;
    }


}
