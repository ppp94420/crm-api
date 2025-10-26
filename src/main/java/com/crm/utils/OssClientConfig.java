package com.crm.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: DELL
 * @Date: 2025/10/26
 * @Version: 1.0
 */
@Configuration
public class OssClientConfig {
    @Value("${aliyun.oss.endpoint}")
    String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    String accessKeySecret;

    @Bean
    public OSSClient createOssClient() {
        return (OSSClient) new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
