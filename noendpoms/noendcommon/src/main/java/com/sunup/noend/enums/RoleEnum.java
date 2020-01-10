package com.sunup.noend.enums;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 9:46 2019/12/16
 */
public enum RoleEnum implements Operation {
    /**
     * 系统管理员
     */
    ROLE_ROOT_ADMIN {
        @Override
        public String op() {
            return "ROLE_ROOT_ADMIN,ROLE_SALES_ADMIN,ROLE_NORMAL,ROLE_USER";
        }
    },
    /**
     * 销售管理员
     */
    ROLE_SALES_ADMIN {
        @Override
        public String op() {
            return "ROLE_SALES_ADMIN";
        }
    },
    /**
     * 普通用户
     */
    ROLE_NORMAL {
        @Override
        public String op() {
            return "ROLE_NORMAL";
        }
    },
    /**
     * 会员
     */
    ROLE_MEMBER {
        @Override
        public String op() {
            return "ROLE_MEMBER";
        }
    }
}
