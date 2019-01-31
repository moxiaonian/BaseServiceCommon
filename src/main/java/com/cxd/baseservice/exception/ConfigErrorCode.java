package com.cxd.baseservice.exception;

/**
 * Created by xiaodong.cheng on 2019/1/31.
 */
public enum ConfigErrorCode {

    /************************************DB操作开始*****************************************/
    DB_INSERT_ERROR("001001","数据库插入错误"),
    DB_ADD_FAIL("001002","数据添加失败"),
    DB_NOT_ENTITY("001003","不存在该条数据"),
    DB_DELETE_ERROR("001004","数据删除错误"),
    DB_UPDATE_ERROR("001005","数据更新错误"),
    DB_FIND_ERROR("001006","数据查找错误"),
    DB_OFFLINE_ERROR("001007","数据下线错误"),
    DB_DATA_ON_TIME_EXIST("001008","该时间段内，已有数据存在"),
    DB_DATA_ERROR("001009", "DB数据错误")
    ;

    private String code;
    private String desc;

    private static final String ERROR_CODE_PREFIX = "CFG";

    ConfigErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return ERROR_CODE_PREFIX + code;
    }

    public String getDesc() {
        return desc;
    }
}
