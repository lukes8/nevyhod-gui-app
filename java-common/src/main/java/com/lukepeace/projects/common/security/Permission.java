package com.lukepeace.projects.common.security;

public final class Permission {

    public final static String ROLE_USER = "ROLE_USER";
    public final static String ROLE_ADMIN = "ROLE_ADMIN";
    public final static String ROLE_TEST = "ROLE_TEST";
    public final static String ROLE_MAINTENANCE = "ROLE_MAINTENANCE";
    public final static String HAS_ROLE_USER = "hasRole('ROLE_USER')";
    public final static String HAS_ROLE_USER_MAINTENANCE = "hasRole('ROLE_USER_MAINTENANCE')";
    public final static String HAS_ROLE_TEST = "hasRole('ROLE_TEST')";
    public final static String HAS_ROLE_ADMIN = "hasRole('ROLE_ADMIN')";
    public final static String OR = " || ";
    public final static String ACCESS_ITEM_DATA = HAS_ROLE_USER + OR + HAS_ROLE_ADMIN;
    public final static String MODIFY_ITEM_DATA = HAS_ROLE_USER + OR + HAS_ROLE_ADMIN;
    public final static String CREATE_ITEM_DATA = HAS_ROLE_USER + OR + HAS_ROLE_ADMIN;
    public final static String UPLOAD_ITEM_DATA = HAS_ROLE_USER + OR + HAS_ROLE_ADMIN;
    public final static String DOWNLOAD_ITEM_DATA = HAS_ROLE_USER + OR + HAS_ROLE_ADMIN;

    public final static String ACCESS_ORDER_DATA = HAS_ROLE_TEST + OR + HAS_ROLE_ADMIN;
    public final static String MODIFY_ORDER_DATA = HAS_ROLE_USER + OR + HAS_ROLE_ADMIN;
    public final static String CREATE_ORDER_DATA = HAS_ROLE_USER + OR + HAS_ROLE_ADMIN;
    public final static String UPLOAD_ORDER_DATA = HAS_ROLE_USER + OR + HAS_ROLE_ADMIN;
    public final static String DOWNLOAD_ORDER_DATA = HAS_ROLE_USER + OR + HAS_ROLE_ADMIN;

    public final static String APPROVE_USER = HAS_ROLE_ADMIN;
    public final static String ACCESS_USER_DATA = HAS_ROLE_USER_MAINTENANCE + OR + HAS_ROLE_ADMIN;
    public final static String MODIFY_USER_DATA = HAS_ROLE_USER_MAINTENANCE;
    public final static String CREATE_USER_DATA = HAS_ROLE_USER_MAINTENANCE;
    public final static String UPLOAD_USER_DATA = HAS_ROLE_USER_MAINTENANCE;
    public final static String DOWNLOAD_USER_DATA = HAS_ROLE_USER_MAINTENANCE + HAS_ROLE_ADMIN;



}
