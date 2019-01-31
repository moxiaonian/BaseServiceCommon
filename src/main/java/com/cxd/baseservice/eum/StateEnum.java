package com.cxd.baseservice.eum;

/**
 * Created by wenter on 2019/1/31.
 */
public enum StateEnum {
    DRAFT(0, "草稿"),
    ONLINE(1, "上线"),
    OFFLINE(2, "下线"),
    PRE_RELEASE(3, "预发布"),
    DELETE(4, "删除");

    private Integer id;
    private String name;
    StateEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
