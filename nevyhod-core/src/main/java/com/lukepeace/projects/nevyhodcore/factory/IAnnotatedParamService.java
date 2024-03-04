package com.lukepeace.projects.nevyhodcore.factory;

public interface IAnnotatedParamService {

//    default String[] getModules() { return this.getClass().getAnnotation(ModuleParam.class).modules(); }
    default String getName() { return this.getClass().getAnnotation(ReportNameParam.class).name(); }
}
