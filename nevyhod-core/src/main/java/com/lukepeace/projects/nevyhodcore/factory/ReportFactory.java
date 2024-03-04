package com.lukepeace.projects.nevyhodcore.factory;

import com.lukepeace.projects.nevyhodcore.service.report.IReportService;

public interface ReportFactory {
    IReportService getReportService(String name);
}
