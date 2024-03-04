package com.lukepeace.projects.nevyhodcore.factory.impl;

import com.lukepeace.projects.nevyhodcore.factory.AbstractServiceFactory;
import com.lukepeace.projects.nevyhodcore.factory.ReportFactory;
import com.lukepeace.projects.nevyhodcore.service.report.IReportService;
import org.springframework.stereotype.Component;

@Component
public class ReportFactoryImpl extends AbstractServiceFactory<IReportService> implements ReportFactory {

    @Override
    public IReportService getReportService(String name) {
        IReportService service = super.getService(name);
        return service;
    }
}
