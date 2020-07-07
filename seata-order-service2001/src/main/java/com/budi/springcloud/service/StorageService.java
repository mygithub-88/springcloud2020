package com.budi.springcloud.service;

import com.budi.springcloud.domain.CommResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageService {
    @PostMapping("/storage/decrease")
    CommResult decrease(@RequestParam("productId")Long productId,@RequestParam("count")Integer count);
}
