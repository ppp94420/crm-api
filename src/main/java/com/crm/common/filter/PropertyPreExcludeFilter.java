package com.crm.common.filter;

import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;

/**
 * @Author: DELL
 * @Date: 2025/11/9
 * @Version: 1.0
 */
public class PropertyPreExcludeFilter extends SimplePropertyPreFilter {
    public PropertyPreExcludeFilter addExclude(String... filters) {
        for (int i = 0; i < filters.length; i++) {
            this.getExcludes().add(filters[1]);
        }
        return this;
    }
}
