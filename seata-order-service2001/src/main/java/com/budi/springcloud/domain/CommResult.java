package com.budi.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommResult<T> {
    private Integer code;
    private String mesage;
    private T data;
    public CommResult(Integer code,String message)
    {
        this(code,message,null);
    }
}
