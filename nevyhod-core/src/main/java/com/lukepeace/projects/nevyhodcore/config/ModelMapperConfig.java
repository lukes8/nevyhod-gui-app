package com.lukepeace.projects.nevyhodcore.config;

import com.lukepeace.projects.nevyhodcore.entity.Item;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
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

        modelMapper.createTypeMap(ItemVO.class, Item.class).setPostConverter(converterVO2Entity).addMappings(mapper -> mapper.skip(Item::setEnabled));
        modelMapper.createTypeMap(Item.class, ItemVO.class).setPostConverter(converterEntity2VO).addMappings(mapper -> mapper.skip(ItemVO::setDisabled));

        return modelMapper;
    }
}