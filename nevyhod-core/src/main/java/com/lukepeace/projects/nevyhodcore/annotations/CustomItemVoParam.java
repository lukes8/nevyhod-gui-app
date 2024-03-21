package com.lukepeace.projects.nevyhodcore.annotations;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Schema(name = "ItemVo4Firebase", description = "This is ItemVO wrapper object for firebase items collection")
public @interface CustomItemVoParam {
}