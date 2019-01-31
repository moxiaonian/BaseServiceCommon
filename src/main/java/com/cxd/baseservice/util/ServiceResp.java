package com.cxd.baseservice.util;

/**
 * Created by wenter on 2019/1/31.
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ServiceResp<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String appId;
    private String respCode;
    private String respMsg;
    private Map<String, Object> errorData;
    private boolean isSuccess = true;
    private T bean;

    /** @deprecated */
    @Deprecated
    public ServiceResp() {
    }

    public static <T> ServiceResp<T> newInstanceSuccess() {
        ServiceResp<T> serviceResp = new ServiceResp();
        serviceResp.setBean(null);
        return serviceResp;
    }

    public static <T> ServiceResp<T> newInstanceSuccess(T bean) {
        ServiceResp<T> serviceResp = new ServiceResp();
        serviceResp.setBean(bean);
        return serviceResp;
    }

    public static <T> ServiceResp<T> newInstanceFail(String msg) {
        return newInstanceFail(Integer.valueOf(500), msg);
    }

    public static <T> ServiceResp<T> newInstanceDataNotFoundFail(String msg) {
        return newInstanceFail(Integer.valueOf(404), msg);
    }

    public static <T> ServiceResp<T> newInstanceFail(Integer code, String msg) {
        return newInstanceFail(code + "", msg);
    }

    public static <T> ServiceResp<T> newInstanceFail(String code, String msg) {
        return newInstanceFail(code, msg, new HashMap());
    }

    public static <T> ServiceResp<T> newInstanceFail(String code, String msg, Map<String, Object> errorData) {
        ServiceResp<T> serviceResp = new ServiceResp();
        serviceResp.setRespCode(code);
        serviceResp.setRespMsg(msg);
        serviceResp.setErrorData(errorData);
        serviceResp.setSuccess(false);
        return serviceResp;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return this.respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public T getBean() {
        return this.bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public Map<String, Object> getErrorData() {
        return this.errorData;
    }

    public void setErrorData(Map<String, Object> errorData) {
        this.errorData = errorData;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public boolean isFailure() {
        return !this.isSuccess();
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

}
