package com.lukepeace.projects.nevyhodcore.util;

import com.lukepeace.projects.common.security.Permission;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import com.lukepeace.projects.nevyhodcore.vo.UserRoleVO;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;
import com.lukepeace.projects.nevyhodcore.vo.pk.UserRolePkVO;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MockDataHelper {
    public static List<UserVO> dummyList4User() {

        List<UserVO> lst = Arrays.asList(
                UserVO.builder().email("luke").password("luke").createdDate(LocalDateTime.now()).name("luke").enabled(true)
                        .roles(Arrays.asList(UserRoleVO.builder().id(UserRolePkVO.builder().email("luke").name(Permission.ROLE_ADMIN).build()).build()) ).build(),
                UserVO.builder().email("tim").password("tim").createdDate(LocalDateTime.now()).name("tim").enabled(true)
                        .roles(Arrays.asList(UserRoleVO.builder().id(UserRolePkVO.builder().email("tim").name(Permission.ROLE_TEST).build()).build()) ).build()
                );
        return lst;
    }

    public static List<UserRoleVO> dummyList4UserRole() {

        List<UserRoleVO> lst = Arrays.asList(UserRoleVO.builder()
                .id(UserRolePkVO.builder().email("luke@green.com").name("green name").build()).build());
        return lst;
    }

    public static List<ItemVO> dummyList4Item(String email) {

        String jpeg = "https://www.sonnentor.com/global/tips/CZ%20only/Zdrav%C3%AD%20-%20Gesundheit/Kop%C5%99iva%20%E2%80%93%20Urtica%20dioica/image-thumb__41991__img-text-teaser-big-default/hlavni-biobylinky-biocaje-biokopriva-kopriva-louky.jpeg";
        long id = 1;
        final int maxItems = 20;
        List<ItemVO> lst = new ArrayList<>();
        lst.addAll(Arrays.asList(
                ItemVO.builder().id(id++).imagePath(jpeg).email(email).title("Kosa").price(100.0).createdDate(LocalDateTime.now()).category("Zahrada").amount(2).build(),
                ItemVO.builder().id(id++).imagePath(jpeg).email(email).title("Kopriva caj").price(100.0).createdDate(LocalDateTime.now()).category("Potraviny").amount(2).build(),
                ItemVO.builder().id(id++).imagePath(jpeg).email(email).title("Nesmeky").price(100.0).createdDate(LocalDateTime.now()).category("Turistika").amount(2).build(),
                ItemVO.builder().id(id++).imagePath(jpeg).email(email).title("Banany").price(100.0).createdDate(LocalDateTime.now()).category("Potraviny").amount(2).build(),
                ItemVO.builder().id(id++).imagePath(jpeg).email(email).title("Jablka").price(100.0).createdDate(LocalDateTime.now()).category("Potraviny").amount(2).build(),
                ItemVO.builder().id(id++).imagePath(jpeg).email(email).title("Karty").price(100.0).createdDate(LocalDateTime.now()).category("Hry").amount(2).build()
                )
        );
        for (int i = 0; i < maxItems; i++) {
            lst.add(ItemVO.builder().id(id+i).imagePath(jpeg).email(email).title("Whatever"+i).price(100.0).createdDate(LocalDateTime.now()).category("Whatever").amount(2).build());
        }
        return lst;
    }

    public static List<com.lukepeace.projects.nevyhodcore.vo.firebase.ItemVO> dummyList4Item4Firebase(String email) {
        ModelMapper mapper = new ModelMapper();
        List<com.lukepeace.projects.nevyhodcore.vo.firebase.ItemVO> lst = dummyList4Item(email).stream().map(o -> mapper.map(o, com.lukepeace.projects.nevyhodcore.vo.firebase.ItemVO.class)).collect(Collectors.toList());
        return lst;
    }


}
