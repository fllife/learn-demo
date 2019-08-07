package com.mxm.java.learn_demo.tools.utils;

import com.mxm.java.learn_demo.tools.enums.ErrorCode;
import com.mxm.java.learn_demo.tools.exception.ExpectedException;
import com.mxm.java.learn_demo.tools.exception.ParameterException;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 参数断言常用类
 * @author maxianming
 * @date 2018/8/3 9:53
 */
public class AssertUtils {
    /**
     * @author maxianming
     * @param 
     * @return 
     * @date 2018/8/3 10:00
     */
    public static void isNull(Object object){
        if (object == null) {
            throw new ParameterException(ErrorCode.NULL_OBJ_ARGUMENT);
        }
    }
    /**
     * @author maxianming
     * @param 
     * @return 
     * @date 2018/8/3 10:00
     */
    public static void isNull(Object... object){
        if (object == null || object.length == 0) {
            throw new ParameterException(ErrorCode.NULL_OBJ_ARGUMENT);
        }
    }
    
    /**
     * @author maxianming
     * @param
     * @return 
     * @date 2018/8/3 11:21
     */
    public static void isTrue(boolean result) {
        if (result) {
            throw new ExpectedException(ErrorCode.SYS_EXCEPTION);
        }
    }
    /**
     * @author maxianming
     * @param
     * @return 
     * @date 2018/8/3 10:00
     */
    public static void isEmpty(Collection collection) {
        if (collection == null || CollectionUtils.isEmpty(collection)) {
            throw new ParameterException(ErrorCode.EMPTY_COLLECTION_ARGUMENT);
        }
    }
}
