package com.lukepeace.projects.nevyhodcore.config;

import com.lukepeace.projects.common.vo.UserDetailsRoleVO;
import com.lukepeace.projects.common.vo.UserRegisterVO;
import com.lukepeace.projects.nevyhodcore.entity.Item;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import com.lukepeace.projects.nevyhodcore.vo.UserRoleVO;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();


        Converter<ItemVO, Item> converterVO2Entity = new Converter<ItemVO, Item>() {
            @Override
            public Item convert(MappingContext<ItemVO, Item> context) {
                if (context != null) {
                    ItemVO source = context.getSource();
                    Item destination = context.getDestination();

                    if (source.getDisabled() != null) {
                        destination.setEnabled(!source.getDisabled());
                    }
                }
                return context.getDestination();
            }
        };

        Converter<Item, ItemVO> converterEntity2VO = new Converter<Item, ItemVO>() {
            @Override
            public ItemVO convert(MappingContext<Item, ItemVO> context) {
                if (context != null) {
                    Item source = context.getSource();
                    ItemVO destination = context.getDestination();

                    if (source.getEnabled() != null) {
                        destination.setDisabled(!source.getEnabled());
                    }
                }
                return context.getDestination();
            }
        };
        Converter<UserVO, UserRegisterVO> converterUserVO2UserRegisterVO = new Converter<UserVO, UserRegisterVO>() {
            @Override
            public UserRegisterVO convert(MappingContext<UserVO, UserRegisterVO> context) {
                UserRegisterVO destination = null;
                if (context != null) {
                    UserVO source = context.getSource();
                    // todo remove password
                    destination = UserRegisterVO.builder().email(source.getEmail()).password(source.getPassword()).username(source.getName()).build();
                    return destination;
                }
                return null;
            }
        };
        Converter<UserRoleVO, UserDetailsRoleVO> converterUserRoleVO2UserDetailsRoleVO = new Converter<UserRoleVO, UserDetailsRoleVO>() {
            @Override
            public UserDetailsRoleVO convert(MappingContext<UserRoleVO, UserDetailsRoleVO> context) {
                UserDetailsRoleVO destination = null;
                if (context != null) {
                    UserRoleVO source = context.getSource();
                    // todo remove password
                    destination = UserDetailsRoleVO.builder().name(source.getId().getName()).build();
                    return destination;
                }
                return null;
            }
        };

        modelMapper.createTypeMap(ItemVO.class, Item.class).setPostConverter(converterVO2Entity).addMappings(mapper -> mapper.skip(Item::setEnabled));
        modelMapper.createTypeMap(Item.class, ItemVO.class).setPostConverter(converterEntity2VO).addMappings(mapper -> mapper.skip(ItemVO::setDisabled));
        modelMapper.createTypeMap(UserVO.class, UserRegisterVO.class).setConverter(converterUserVO2UserRegisterVO);
        modelMapper.createTypeMap(UserRoleVO.class, UserDetailsRoleVO.class).setConverter(converterUserRoleVO2UserDetailsRoleVO);

        return modelMapper;
    }
}