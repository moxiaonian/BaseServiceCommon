package com.cxd.baseservice.util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wenter on 2017/6/15.
 */
public class ConvertBeanUtil {

    /**
     * convent bean to toBean. in it ,Enum to Integer, Integer to Enum,just convert by enum id
     * @param bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Object convert(T bean, Class<?> clazz){
        Object toBean = null;
        try {
            toBean = clazz.newInstance();
            Field[] beanFields = bean.getClass().getDeclaredFields();
            for(Field beanField : beanFields){
                try {
                    beanField.setAccessible(true);
                    Object value = beanField.get(bean);
                    Field toBeanField = clazz.getDeclaredField(beanField.getName());
                    toBeanField.setAccessible(true);
                    if(value !=null && beanField.getType().isEnum() && toBeanField.getType() == Integer.class ){
                        Field enumFiled = beanField.getType().getDeclaredField("id");
                        enumFiled.setAccessible(true);
                        toBeanField.set(toBean,enumFiled.get(value));
                    }else if(value !=null && toBeanField.getType().isEnum() && beanField.getType() == Integer.class){
                        try {
                            Method enumMethod = toBeanField.getType().getMethod("getEnumById",Integer.class);
                            enumMethod.setAccessible(true);
                            toBeanField.set(toBean,enumMethod.invoke(null,value));
                        } catch (InvocationTargetException e) {
                            continue;
                        } catch (NoSuchMethodException e) {
                            continue;
                        }
                    }else {
                        toBeanField.set(toBean, value);
                    }
                } catch (IllegalArgumentException e) {
                    continue;
                } catch (IllegalAccessException e) {
                    continue;
                } catch (NoSuchFieldException e) {
                    continue;
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        return toBean;
    }


}
