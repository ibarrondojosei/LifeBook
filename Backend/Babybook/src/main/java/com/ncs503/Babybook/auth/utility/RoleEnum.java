package com.ncs503.Babybook.auth.utility;

public enum RoleEnum {
    USER, GUEST;

    private static final String PREFIX = "ROLE_";

    public String getFullRoleName(){
        return PREFIX + name();
    }
    public String getSimpleRoleName(){
        return name();
    }
}
