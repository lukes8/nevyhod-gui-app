package com.lukepeace.projects.nevyhodgui.web.controller.rest.monitoring;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.factory.ReportFactory;
import com.lukepeace.projects.nevyhodcore.service.report.IReportService;
import com.lukepeace.projects.nevyhodcore.util.GeneralReportInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(("/rest/api/report"))
public class ReportController {

    private IReportService itemService;

    private IReportService orderService;

    @Autowired private ReportFactory reportFactory;

    @Autowired
    public ReportController(@Qualifier("itemReportService") IReportService itemService, @Qualifier("orderReportService") IReportService orderService) {
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<InputStreamResource> getReport(String reportName, Long id) throws IOException, GeneralException {

        IReportService reportService = reportFactory.getReportService(reportName);
        GeneralReportInfo info = reportService.getReportJSON(id);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=" + info.getFileName());
        return new ResponseEntity<>(new InputStreamResource(info.getIs()), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/item-report/export")
    public ResponseEntity<InputStreamResource> export(@RequestParam(required = true) Long id) throws IOException, GeneralException {

        GeneralReportInfo info = itemService.getReportJSON(id);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=" + info.getFileName());
        return new ResponseEntity<>(new InputStreamResource(info.getIs()), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/order-report/export")
    public ResponseEntity<InputStreamResource> exportOrder(@RequestParam(required = true) Long id) throws IOException, GeneralException {

        GeneralReportInfo info = orderService.getReportJSON(id);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=" + info.getFileName());
        return new ResponseEntity<>(new InputStreamResource(info.getIs()), responseHeaders, HttpStatus.OK);
    }
}
