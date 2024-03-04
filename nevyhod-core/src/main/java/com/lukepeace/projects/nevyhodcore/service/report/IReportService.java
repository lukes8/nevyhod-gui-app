package com.lukepeace.projects.nevyhodcore.service.report;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.factory.IAnnotatedParamService;
import com.lukepeace.projects.nevyhodcore.util.GeneralReportInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedOutputStream;

public interface IReportService extends IAnnotatedParamService {

    default InputStream getReportPOI(Long id) {
        throw new RuntimeException("missing implementation");
    }

    default GeneralReportInfo getReportJSON(Long id) throws IOException, GeneralException {
        throw new RuntimeException("missing implementation");
    }

    default void streamPOIReport(PipedOutputStream pos, Long id) {
        throw new RuntimeException("missing implementation");
    }
}
