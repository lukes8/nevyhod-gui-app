package com.lukepeace.projects.nevyhodcore.service.report.impl;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.criteria.OrderCriteria;
import com.lukepeace.projects.nevyhodcore.entity.Order;
import com.lukepeace.projects.nevyhodcore.factory.ReportNameParam;
import com.lukepeace.projects.nevyhodcore.repository.OrderRepository;
import com.lukepeace.projects.nevyhodcore.service.AbstractServiceImpl;
import com.lukepeace.projects.nevyhodcore.service.report.IReportService;
import com.lukepeace.projects.nevyhodcore.util.GeneralReportInfo;
import com.lukepeace.projects.nevyhodcore.util.OrderFilter;
import com.lukepeace.projects.nevyhodcore.vo.OrderVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Data
@Service @Qualifier("orderReportService")
@Slf4j
@ReportNameParam(name = "order-report")
public class OrderReportServiceImpl extends AbstractServiceImpl<Order, OrderVO, OrderRepository, Long, OrderFilter, OrderCriteria> implements IReportService {

    @Override
    public GeneralReportInfo getReportJSON(Long id) throws IOException, GeneralException {

        String name = getName();
        String ext = ".json";
        File tempFile = File.createTempFile("json-report", ".json");

        OrderFilter filter = new OrderFilter(Pageable.ofSize(1));
        filter.setId(id);
        Page<OrderVO> lst = super.findAll(filter);
        super.getObjectMapper().writeValue(tempFile, lst.getContent());

        GeneralReportInfo info = GeneralReportInfo.builder().name(name).extension(ext).is(new FileInputStream(tempFile)).build();
        return info;
    }
}
