package com.budi.springcloud.service.impl;

import com.budi.springcloud.dao.StorageDao;
import com.budi.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("减库存开始");
        storageDao.decrease(productId,count);
        log.info("---------->减库存结束");
    }
}
