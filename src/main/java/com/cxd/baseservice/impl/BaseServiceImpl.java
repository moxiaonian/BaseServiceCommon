package com.cxd.baseservice.impl;

import com.cxd.baseservice.BaseService;
import com.cxd.baseservice.eum.StateEnum;
import com.cxd.baseservice.exception.ConfigErrorCode;
import com.cxd.baseservice.mapper.BaseMapper;
import com.cxd.baseservice.util.CommonUtil;
import com.cxd.baseservice.util.ConvertBeanUtil;
import com.cxd.baseservice.util.ServiceResp;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;

/**
 * Created by wenter on 2019/1/31.
 */
public abstract class BaseServiceImpl<T,D,E,ID extends Serializable> implements BaseService<T,ID> {

    public abstract BaseMapper<D, E, ID> getMapper();

    private final static Integer ACTIVE = 1;
    private final static Integer NOACTIVE = 0;
    private final static Integer INITSTATE = StateEnum.DRAFT.getId();

    private Class<T> beanClass = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    private Class<D> entityClass = (Class<D>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    private Class<ID> idClass = (Class<ID>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[3];

    @Override
    public ServiceResp<T> addOne(T record) {
        D entity = (D) ConvertBeanUtil.convert(record,entityClass);
        Field[] fields = entity.getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            try {
                if(field.getName().equals("active")) field.set(entity,ACTIVE);
                if (field.getName().equals("operateTime")) field.set(entity,new Date());
                if(field.getName().equals("state")) field.set(entity,INITSTATE);
            }catch (Exception e){
                continue;
            }
        }
        int result;
        try {
            result = getMapper().insert(entity);
        }catch(Exception e){
            return CommonUtil.generateFailureServiceResp(e, ConfigErrorCode.DB_INSERT_ERROR);
        }
        if(result > 0){
            try {
                Method m1 = record.getClass().getMethod("setId",idClass);
                Method m2 = entity.getClass().getMethod("getId");
                m1.invoke(record,(ID)m2.invoke(entity));
            } catch (Exception e) {
                return CommonUtil.generateFailureServiceResp(e, ConfigErrorCode.DB_ADD_FAIL);
            }
            return CommonUtil.generateSuccessServiceResp(record);
        }
        return CommonUtil.generateFailureServiceResp(record.getClass().getSimpleName()+" add error!", ConfigErrorCode.DB_ADD_FAIL);
    }

    @Override
    public ServiceResp deleteOneById(ID id) {
        D entity = getMapper().selectByPrimaryKey(id);
        if(entity == null){
            return CommonUtil.generateFailureServiceResp("delete error!", ConfigErrorCode.DB_NOT_ENTITY);
        }
        try {
            Method activeMethod = entity.getClass().getMethod("setActive",Integer.class);
            activeMethod.setAccessible(true);
            activeMethod.invoke(entity,NOACTIVE);
        } catch (Exception e) {
            return CommonUtil.generateFailureServiceResp(e,ConfigErrorCode.DB_DELETE_ERROR);
        }
        int result;
        try {
            result = getMapper().updateByPrimaryKeySelective(entity);
        }catch(Exception e){
            return CommonUtil.generateFailureServiceResp(e, ConfigErrorCode.DB_DELETE_ERROR);
        }
        return (result > 0)? CommonUtil.generateSuccessServiceResp(true) : CommonUtil.generateFailureServiceResp("delete error!", ConfigErrorCode.DB_DELETE_ERROR);
    }

    @Override
    public ServiceResp updateOne(T record) {
        D entity = (D)ConvertBeanUtil.convert(record,entityClass);
        int result;
        try {
            result = getMapper().updateByPrimaryKeySelective(entity);
        } catch (Exception e) {
            return CommonUtil.generateFailureServiceResp(e, ConfigErrorCode.DB_UPDATE_ERROR);
        }
        if(result > 0){
            try {
                Method m1 = record.getClass().getMethod("setId",idClass);
                Method m2 = entity.getClass().getMethod("getId");
                m1.invoke(record,(ID)m2.invoke(entity));
            } catch (Exception e) {
                return CommonUtil.generateFailureServiceResp(e, ConfigErrorCode.DB_UPDATE_ERROR);
            }
            return CommonUtil.generateSuccessServiceResp(record);
        }
        return CommonUtil.generateFailureServiceResp("update error!", ConfigErrorCode.DB_UPDATE_ERROR);
    }

    @Override
    public ServiceResp findOneById(ID id) {
        D entity = getMapper().selectByPrimaryKey(id);
        try {
            if(entity == null || entity.getClass().getMethod("getActive").invoke(entity) == NOACTIVE){
                return CommonUtil.generateFailureServiceResp("find none!", ConfigErrorCode.DB_NOT_ENTITY);
            }
        }catch (Exception e){
            return CommonUtil.generateFailureServiceResp(e, ConfigErrorCode.DB_FIND_ERROR);
        }
        T bean = (T)ConvertBeanUtil.convert(entity, beanClass);
        return CommonUtil.generateSuccessServiceResp(bean);
    }

}
