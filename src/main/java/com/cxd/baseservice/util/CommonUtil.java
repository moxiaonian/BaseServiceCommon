package com.cxd.baseservice.util;

import com.cxd.baseservice.exception.ConfigErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wenter on 2019/1/31.
 */
public class CommonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

    public static ServiceResp generateFailureServiceResp(Object obj, ConfigErrorCode configErrorCode) {
        ServiceResp resp = new ServiceResp();
        if (obj instanceof Exception) {
            LOGGER.error("出现了未知错误：{}", (Exception) obj);
        }
        if (obj instanceof String) {
            LOGGER.error((String) obj);
        }
        resp.setSuccess(false);
        resp.setRespCode(configErrorCode.getCode());
        resp.setRespMsg(configErrorCode.getDesc());
        return resp;
    }

    public static ServiceResp generateSuccessServiceResp(Object bean){
        ServiceResp resp = ServiceResp.newInstanceSuccess();
        resp.setSuccess(true);
        resp.setBean(bean);
        return resp;
    }
}
