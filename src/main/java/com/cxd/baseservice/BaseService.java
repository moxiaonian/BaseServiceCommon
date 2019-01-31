package com.cxd.baseservice;

import com.cxd.baseservice.util.ServiceResp;

import java.io.Serializable;

/**
 * Created by wenter on 2019/1/31.
 */
public interface BaseService<T,ID extends Serializable> {

    ServiceResp<T> addOne(T record);
    ServiceResp<T> deleteOneById(ID id);
    ServiceResp<T> updateOne(T record);
    ServiceResp<T> findOneById(ID id);

}
