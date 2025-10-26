package com.crm.schedule;

import com.crm.query.ProductQuery;
import com.crm.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: DELL
 * @Date: 2025/10/26
 * @Version: 1.0
 */
@Component
@AllArgsConstructor
public class TimerJob {
    private final ProductService productService;

    @Scheduled(fixedRate = 1000 * 60)
    public void batchUpdateProductState() {
        productService.batchUpdateProductState();
    }
}
