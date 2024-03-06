package com.lukepeace.projects.nevyhodcore.util;

import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import com.lukepeace.projects.nevyhodcore.vo.UserRoleVO;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;
import com.lukepeace.projects.nevyhodcore.vo.pk.UserRolePkVO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MockDataHelper {
    public static List<UserVO> dummyList4User() {

        List<UserVO> lst = Arrays.asList(
                UserVO.builder().email("luke").createdDate(LocalDateTime.now()).name("luke").enabled(true)
                        .roles(Arrays.asList(UserRoleVO.builder().id(UserRolePkVO.builder().email("luke").name("ROLE_ADMIN").build()).build()) ).build(),
                UserVO.builder().email("tim@green.com").createdDate(LocalDateTime.now()).name("tim").enabled(true).build()
        );
        return lst;
    }

    public static List<UserRoleVO> dummyList4UserRole() {

        List<UserRoleVO> lst = Arrays.asList(UserRoleVO.builder()
                .id(UserRolePkVO.builder().email("luke@green.com").name("green name").build()).build());
        return lst;
    }

    public static List<ItemVO> dummyList4Item(String email) {

        List<ItemVO> lst = Arrays.asList(
                ItemVO.builder().email(email).title("Kosa").price(100.0).createdDate(LocalDateTime.now()).category("Zahrada").amount(2).build(),
                ItemVO.builder().email(email).title("Kopriva caj").price(100.0).createdDate(LocalDateTime.now()).category("Potraviny").amount(2).build(),
                ItemVO.builder().email(email).title("Nesmeky").price(100.0).createdDate(LocalDateTime.now()).category("Turistika").amount(2).build(),
                ItemVO.builder().email(email).title("Banany").price(100.0).createdDate(LocalDateTime.now()).category("Potraviny").amount(2).build(),
                ItemVO.builder().email(email).title("Jablka").price(100.0).createdDate(LocalDateTime.now()).category("Potraviny").amount(2).build(),
                ItemVO.builder().email(email).title("Karty").price(100.0).createdDate(LocalDateTime.now()).category("Hry").amount(2).build()

        );
        return lst;
    }


}
