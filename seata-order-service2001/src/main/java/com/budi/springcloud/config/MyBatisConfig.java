package com.budi.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.budi.springcloud.dao")
public class MyBatisConfig {
}
