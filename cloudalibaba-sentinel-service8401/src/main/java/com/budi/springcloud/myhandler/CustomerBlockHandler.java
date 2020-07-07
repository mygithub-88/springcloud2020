package com.budi.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.budi.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public CommonResult handelException(BlockException exception)
    {
        return new CommonResult(444,"客户自定义，global--e1");
    }
    public CommonResult handelException2(BlockException exception)
    {
        return new CommonResult(444,"客户自定义，global--e2");
    }
}
