package com.batman.gexinoauth2.common.enumtype;

import org.springframework.util.StringUtils;

public interface RoleTypeEnum {

    enum RoleType{

        SUPERADMIN(1,"superadmin"),
        ADMIN(2,"admin"),
        OPERATOR(3,"operator"),
        ;
        private int value;
        private String lable;

        RoleType(int value, String lable) {
            this.value = value;
            this.lable = lable;
        }

        public int getValue() { return value; }

        public String getLable(){
            return lable;
        }
    }

    //角色类型码表转换
    public static int roleTypeChangeInt(String roleType){
        int rloe = 0;
        if (!StringUtils.isEmpty(roleType)){
            if ("superadmin".equalsIgnoreCase(roleType)){
                rloe = RoleType.SUPERADMIN.getValue();
            }else if ("admin".equalsIgnoreCase(roleType)){
                rloe = RoleType.ADMIN.getValue();
            }else if ("operator".equalsIgnoreCase(roleType)){
                rloe = RoleType.OPERATOR.getValue();
            }
        }
        return rloe;
    }

    //角色类型码表转换
    public static String roleTypeChangeStr(Integer roleType){
        String roleTypes = null;
        if (roleType!=null){
            if (1==roleType){
                roleTypes = RoleType.SUPERADMIN.getLable();
            }else if (2 == roleType){
                roleTypes = RoleType.ADMIN.getLable();
            }else if (3 == roleType){
                roleTypes = RoleType.OPERATOR.getLable();
            }
        }
        return roleTypes;
    }
}
