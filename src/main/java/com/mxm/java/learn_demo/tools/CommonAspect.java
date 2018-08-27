package com.mxm.java.learn_demo.tools;

import com.alibaba.fastjson.JSON;
import com.mxm.java.learn_demo.tools.annotation.AccessService;
import com.mxm.java.learn_demo.tools.enums.ErrorCode;
import com.mxm.java.learn_demo.tools.exception.ExpectedException;
import com.mxm.java.learn_demo.tools.exception.ParameterException;
import com.mxm.java.learn_demo.tools.exception.SystemException;
import com.mxm.java.learn_demo.tools.monitor.ExceptionMesssage;
import com.mxm.java.learn_demo.tools.monitor.ExceptionReport;
import com.mxm.java.learn_demo.tools.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 公共拦截器 1、异常拦截器、2、日志打印
 */
@Aspect
@Configuration
@ConditionalOnBean(annotation = {AccessService.class})
public class CommonAspect {

    private Logger logger = LoggerFactory.getLogger(CommonAspect.class);

    // 校验器
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired(required = false)
    private ExceptionReport exceptionReport;

    @Pointcut("@within(com.mxm.java.learn_demo.tools.annotation.AccessService) || @annotation(com.mxm.java.learn_demo.tools.annotation.AccessService)")
    public void controllerPointCut() {

    }


    @Around("controllerPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Class returnType = method.getReturnType();
        String[] paramNames = methodSignature.getParameterNames();
        Object[] paramObj = joinPoint.getArgs();
        // 1、注入基础请求参数
        // setBaseReq2ParamObj(OAuth2Resolver.getWrapper(), paramObj);

        // 2、拼接请求参数
        String paramStr = LogUtils.jointMethodParam(paramNames, paramObj);

        long startMillis = System.currentTimeMillis();
        logger.info("request method:[{}], params:[{}]", method.getDeclaringClass().getSimpleName() + "." + method.getName(), paramStr);
        Object result;
        try {
            // 3、参数校验
            paramValidate(paramObj);
            // 4、执行方法
            result = joinPoint.proceed(paramObj);
        } catch (Throwable throwable) {
            ResultMessage msg ;
            if (throwable instanceof ParameterException) {
                ParameterException exception = (ParameterException) throwable;
                logger.info("参数校验异常,错误码: " + exception.getErrorCode() + ", 错误信息: " + exception.getErrorMessage() + ", 异常原因: ", exception);
                msg = new ResultMessage(exception.getErrorMessage(), exception.getErrorCode());
            } else if (throwable instanceof ExpectedException) {
                ExpectedException exception = (ExpectedException) throwable;
                logger.info("系统自定义,返回码: " + exception.getErrorCode() + ", 返回信息: " + exception.getErrorMessage());
                msg = new ResultMessage(exception.getErrorMessage(), exception.getErrorCode());
            } else if (throwable instanceof SystemException) {
                SystemException exception = (SystemException) throwable;
                logger.error("系统处理异常,错误码: " + exception.getErrorCode() + ", 错误信息: " + exception.getErrorMessage() + ", 异常原因: ", exception);
                msg = new ResultMessage(exception.getErrorMessage(), exception.getErrorCode());
            } else {
                logger.error("系统异常,异常原因: ", throwable);
                msg = new ResultMessage(ErrorCode.UNKONWN_ERROR.getMsg(), ErrorCode.UNKONWN_ERROR.getCode());
            }

            // 上报异常
            if (exceptionReport != null) {
                exceptReport(throwable);
            }
            if (ClassUtils.isAssignable(returnType, ResultMessage.class)) {
                Object baseReturn = returnType.newInstance();
                BeanUtils.copyProperties(msg, baseReturn);
                logger.info("response:{}, 耗时:{}ms", JSON.toJSON(baseReturn), System.currentTimeMillis() - startMillis);
                return baseReturn;
            }
            throw throwable;
        }

        logger.info("response:{}, 耗时:{}ms", JSON.toJSON(result), System.currentTimeMillis() - startMillis);
        return result;
    }

    private void exceptReport(Throwable throwable) {
        ExceptionMesssage exceptionMesssage = new ExceptionMesssage();
        exceptionMesssage.setThrowable(throwable);
        exceptionMesssage.setOccurTime(new Date());
        exceptionReport.pushMessage(exceptionMesssage);
    }

    /**
     * @author maxianming
     * @param
     * @return
     * date 2018/7/25 14:12
     */
    /*void setBaseReq2ParamObj(Optional<OAuth2Wrapper> oAuth2Wrapper, Object[] paramObj) {
        if (oAuth2Wrapper.isPresent()) {
            OAuth2UserDetail oAuth2UserDetail = oAuth2Wrapper.get().getUserDetail();
            OAuth2TokenDetail oAuth2TokenDetail = oAuth2Wrapper.get().getTokenDetail();
            String userId = null;
            String phone = null;
            if (oAuth2UserDetail != null) {
                userId =  oAuth2UserDetail.getUserId();
                phone = oAuth2UserDetail.getPhone();
            }
            String userToken = null;
            String address = null;
            if (oAuth2TokenDetail != null) {
                userToken = oAuth2TokenDetail.getToken();
                address = oAuth2TokenDetail.getAddress();
            }
            if (paramObj != null && paramObj.length > 0) {
                for (Object param : paramObj) {
                    if (param instanceof BaseReq) {
                        BaseReq baseReq = (BaseReq) param;
                        baseReq.setUserId(userId);
                        baseReq.setUserToken(userToken);
                        baseReq.setPhone(phone);
                        baseReq.setAddress(address);
                        logger.info("用户信息userToken->{},userId->{},phone->{},address->{}",userToken, userId, phone, address);
                    }
                }
            }
        }
    }*/


    /**
     * 参数校验
     */
    private void paramValidate(Object[] params) {
        for (int i = 0; i < params.length; i++) {
            Object req = params[i];
            if (req != null) {
                Class<?> classType = req.getClass();
                if (String.class.equals(classType) || Integer.class.equals(classType) || Boolean.class
                        .equals(classType)) {
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
                    throw new ParameterException(ErrorCode.INVALIAD_PARAM.getCode(),
                            ErrorCode.INVALIAD_PARAM.getMsg() + ": {" + buffer + "}");
                }
            }
        }
    }
}
