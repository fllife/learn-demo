package com.mxm.java.learn_demo.valid;

import com.mxm.java.learn_demo.valid.enums.ErrorCode;
import com.mxm.java.learn_demo.valid.exception.BusinessException;
import com.mxm.java.learn_demo.valid.exception.ParameterException;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

/**
 * 统一异常拦截
 */
@Aspect
@Component
public class CommonAspect {
    private Logger logger = LoggerFactory.getLogger(CommonAspect.class);
    // 校验器
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Around("")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object[] params = joinPoint.getArgs();
        Class returnType = method.getReturnType();
        Object result;
        try {
            paramValidate(params);
            result = joinPoint.proceed();
        } catch (Throwable throwable) {

            BaseResp baseResp = null;
            if (throwable instanceof ParameterException) {
                ParameterException exception = (ParameterException) throwable;
                logger.info("参数校验异常,错误码: " + exception.getErrorCode() +", 错误信息: " + exception.getErrorMessage() + ", 异常原因: ",exception);
                baseResp = new BaseResp(false, exception.getErrorCode(), exception.getErrorMessage());
            } else if (throwable instanceof BusinessException) {
                BusinessException exception = (BusinessException) throwable;
                logger.warn("业务处理异常,错误码: " + exception.getErrorCode() +", 错误信息: " + exception.getErrorMessage() + ", 异常原因: ",exception);
                baseResp = new BaseResp(false, exception.getErrorCode(), exception.getErrorMessage());
            } else  {
                logger.error("系统异常,异常原因: ", throwable);
                baseResp = new BaseResp(false, ErrorCode.UNKONWN_ERROR);
            }
            if (ClassUtils.isAssignable(returnType, BaseResp.class)) {
                Object baseReturn = returnType.newInstance();
                BeanUtils.copyProperties(baseReturn,baseResp);
                return baseReturn;
            }
            throw throwable;
        }
        return result;
    }

    /**
     * 参数校验
     * @param params
     */
    private void paramValidate(Object[] params) {
        for (int i = 0; i < params.length; i++) {
            Object req = params[i];
            if (req != null) {
                Class<?> classType = req.getClass();
                if (String.class.equals(classType) || Integer.class.equals(classType) || Boolean.class.equals(classType)) {
                    continue;
                }
                StringBuffer buffer = new StringBuffer();
                Set<ConstraintViolation<Object>> constraintViolations = validator.validate(req);
                Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();
                while (iter.hasNext()) {
                    ConstraintViolation<Object> constraintViolation = iter.next();
                    String property = constraintViolation.getPropertyPath().toString();
                    String message = constraintViolation.getMessage();
                    buffer.append(property + ":" + message + ", ");
                }
                if (StringUtils.isNotEmpty(buffer.toString())) {
                    throw new ParameterException(ErrorCode.INVALIAD_PARAM.getCode(), ErrorCode.INVALIAD_PARAM.getDesc() + ": {" + buffer + "}");
                }
            }
        }
    }
}
