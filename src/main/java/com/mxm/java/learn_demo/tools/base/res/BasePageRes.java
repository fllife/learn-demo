package com.mxm.java.learn_demo.tools.base.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * dauthor maxianming
 * date 2018/7/10 16:00
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasePageRes<T> implements Serializable {

    // 当前页码
    private Integer pageNum;

    // 总记录数
    private Long total;

    // 当页数
    private Integer pages;

    // 分页数据
    private List<T> data;

    public BasePageRes() {

    }
    public BasePageRes(Integer pageNum, Integer pages, Long total) {
        this.pageNum = pageNum;
        this.pages = pages;
        this.total = total;
    }
    public BasePageRes(Page page) {
        this.pageNum = page.getPageNum();
        this.pages = page.getPages();
        this.total = page.getTotal();
    }
}
