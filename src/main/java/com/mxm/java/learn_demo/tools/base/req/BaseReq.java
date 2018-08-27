package com.mxm.java.learn_demo.tools.base.req;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * author maxianming
 * date 2018/6/12 13:03
 */
@Data
public class BaseReq implements Serializable {

    @NotEmpty(message = "用户ID不能为空")
    private String userId;

    @NotEmpty(message = "用户TOKEN不能为空")
    private String userToken;

}
