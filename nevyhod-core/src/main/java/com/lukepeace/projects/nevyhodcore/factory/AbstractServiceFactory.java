package com.lukepeace.projects.nevyhodcore.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AbstractServiceFactory<T extends IAnnotatedParamService> {

    @Autowired private ApplicationContext applicationContext;
    private Type getType() {
        Type t = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return t;
    }
    public T getService(String name) {

        T service = applicationContext.getBeansWithAnnotation(ReportNameParam.class)
                .values().stream().map(o -> (T) o)
                .filter(s -> s.getName() == null || name.equals(s.getName()))
                .findFirst()
                .orElse(null);
        return service;
    }
}
