package com.imooc.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回的最外层对象
 */

@Data
public class ResultVo<T> implements Serializable{

    private static final long serialVersionUID = -5638414267303610471L;

    /** 结果码 **/
    private Integer code;

    /** 提示信息 **/
    private String msg;

    /** 返回内容 **/
    private T data;
}
