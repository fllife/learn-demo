package com.mxm.java.learn_demo.tools.utils;

import com.mxm.java.learn_demo.tools.exception.SystemException;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 *  亮啦常用工具类
 * @author maxianming
 * @date 2019/2/19 11:39
 */
public class LLBeanUtils extends org.springframework.beans.BeanUtils {


    /**
     * 忽略源对象中的空属性，source中null属性不复制到target
     * @author maxianming
     * @date 2019/2/19 11:43
     */
    public static <T> T copyPropertiesIgnoreNull(Object source, Class<T> targetClass) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(source, "targetClass must not be null");
        T target;
        try {
            target = ConstructorUtils.invokeConstructor(targetClass);
        } catch (Exception e) {
            throw new SystemException("LLBeanUtils ERROR", "实例化对象" + targetClass.getSimpleName() +"error");
        }
        copyPropertiesIgnoreNull(source,target);
        return target;
    }

    /**
     * @author maxianming
     * @param
     * @return
     * @date 2019/2/22 8:07
     */
    public static <T> T copyPropertiesIgnoreNull(Object source, Class<T> targetClass, String... ignoreProperties) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(targetClass, "targetClass must not be null");
        T target;  try {
            target = ConstructorUtils.invokeConstructor(targetClass);
        } catch (Exception e) {
            throw new SystemException("LLBeanUtils ERROR", "实例化对象" + targetClass.getSimpleName() +"error");
        }
        copyPropertiesIgnoreNull(source,target, ignoreProperties);
        return target;
    }

    /**
     * 忽略源对象中的空属性，source中null属性不复制到target
     * @author maxianming
     * @param
     * @return
     * @date 2019/2/19 11:43
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
                        if (value != null) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
                }
            }
        }
    }

    /**
     * 忽略源对象中的空属性，source中null属性不复制到target,同时支持忽略制定属性
     * @author maxianming
     * @param
     * @return
     * @date 2019/2/19 11:43
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target,  String... ignoreProperties) {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
                            if (value != null) {
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                writeMethod.invoke(target, value);
                            }

                        }
                        catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }
}
