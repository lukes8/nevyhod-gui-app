package com.lukepeace.projects.nevyhodcore.service.report.impl;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.criteria.ItemCriteria;
import com.lukepeace.projects.nevyhodcore.entity.Item;
import com.lukepeace.projects.nevyhodcore.factory.ReportNameParam;
import com.lukepeace.projects.nevyhodcore.repository.ItemRepository;
import com.lukepeace.projects.nevyhodcore.service.AbstractServiceImpl;
import com.lukepeace.projects.nevyhodcore.service.report.IReportService;
import com.lukepeace.projects.nevyhodcore.util.GeneralReportInfo;
import com.lukepeace.projects.nevyhodcore.util.ItemFilter;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Data
@Service @Qualifier("itemReportService")
@Slf4j
@ReportNameParam(name = "item-report")
public class ItemReportServiceImpl extends AbstractServiceImpl<Item, ItemVO, ItemRepository, Long, ItemFilter, ItemCriteria> implements IReportService {

    @Override
    public GeneralReportInfo getReportJSON(Long id) throws IOException, GeneralException {

        String name = getName();
        String ext = ".json";
        File tempFile = File.createTempFile("json-report", ".json");

        ItemFilter filter = new ItemFilter(Pageable.ofSize(1));
        filter.setId(id);
        Page<ItemVO> lst = super.findAll(filter);
        super.getObjectMapper().writeValue(tempFile, lst.getContent());

        GeneralReportInfo info = GeneralReportInfo.builder().name(name).extension(ext).is(new FileInputStream(tempFile)).build();
        return info;
    }


}
