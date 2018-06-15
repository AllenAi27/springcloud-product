package com.imooc.product.util;


import com.imooc.product.vo.ResultVo;

public class ResultVoUtil {

    public static ResultVo success(Object o){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(o);
        return resultVo;
    }

    public static ResultVo success(){
        return success(null);
    }

    public static ResultVo fail(Integer code, String message){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(message);
        return resultVo;
    }
}
