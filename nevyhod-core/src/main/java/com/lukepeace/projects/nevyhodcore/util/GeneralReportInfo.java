package com.lukepeace.projects.nevyhodcore.util;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Data @Builder
public class GeneralReportInfo {
    private String name;
    private String group;

    private String extension;

    private InputStream is;

    public String getFileName() {
        return name + extension;
    }
}
