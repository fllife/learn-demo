package com.mxm.java.learn_demo.tools.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/***
 *  Created with IntelliJ IDEA.
 *  @author :  xiamo
 *  Date:  2017-11-16
 *  Time: 11:14
 *  Description:
 **/
public class CommonUtils {
    private CommonUtils() {
    }


    public static Map<String, Object> introspect(Object obj) throws Exception {
        Map<String, Object> result = new HashMap<>();
        BeanInfo info = Introspector.getBeanInfo(obj.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            if (pd.getPropertyType().isInstance(Class.class)) {
                continue;
            }
            Method reader = pd.getReadMethod();
            if (reader != null) {
                result.put(pd.getName(), reader.invoke(obj));
            }
        }
        return result;
    }
}
