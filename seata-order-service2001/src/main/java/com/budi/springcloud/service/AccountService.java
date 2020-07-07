package com.budi.springcloud.service;

import com.budi.springcloud.domain.CommResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountService {
    @PostMapping("/account/decrease")
    CommResult decrease(@RequestParam("userId")Long userId, @RequestParam("money") BigDecimal money);
}
