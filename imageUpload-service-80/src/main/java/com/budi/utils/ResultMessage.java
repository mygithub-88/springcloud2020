package com.budi.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectname:cloud2020
 * @description:
 * @author:zhaoyongquan
 * @date:2020-08-17 14:02:14:02
 **/
@Data
public class ResultMessage {
    private Integer status;
    private String message;
    private Object data;
    public ResultMessage(Integer status,String message,Object data){
        this.status=status;
        this.message=message;
        this.data=data;
    }
}
