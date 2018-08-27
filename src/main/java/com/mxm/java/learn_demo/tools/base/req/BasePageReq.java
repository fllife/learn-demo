package com.mxm.java.learn_demo.tools.base.req;

import lombok.Data;

/**
 * author maxianming
 * date 2018/7/10 15:25
 */
@Data
public class BasePageReq extends BaseReq {

    private Integer page = 1;

    private Integer pageSize = 20;
}
