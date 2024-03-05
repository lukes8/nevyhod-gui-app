package com.lukepeace.projects.common.security;

public final class Permission {

    public final static String USER = "hasRole('ROLE_USER')";
    public final static String ADMIN = "hasRole('ROLE_ADMIN')";
    public final static String USER_MAINTENANCE = "hasRole('ROLE_USER_MAINTENANCE')";
    public final static String TEST = "hasRole('ROLE_TEST')";
    public final static String ACCESS_ITEM_DATA = USER;
    public final static String MODIFY_ITEM_DATA = USER;
    public final static String CREATE_ITEM_DATA = USER;
    public final static String UPLOAD_ITEM_DATA = USER;
    public final static String DOWNLOAD_ITEM_DATA = USER;

    public final static String ACCESS_ORDER_DATA = TEST;
    public final static String MODIFY_ORDER_DATA = USER;
    public final static String CREATE_ORDER_DATA = USER;
    public final static String UPLOAD_ORDER_DATA = USER;
    public final static String DOWNLOAD_ORDER_DATA = USER;

    public final static String ACCESS_USER_DATA = USER_MAINTENANCE;
    public final static String MODIFY_USER_DATA = ADMIN;
    public final static String CREATE_USER_DATA = ADMIN;
    public final static String UPLOAD_USER_DATA = ADMIN;
    public final static String DOWNLOAD_USER_DATA = ADMIN;



}
