package com.crm.service;

import com.crm.common.result.PageResult;
import com.crm.entity.OperLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crm.query.OperLogQuery;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author crm
 * @since 2025-10-12
 */
public interface OperLogService extends IService<OperLog> {
    /**
     * 记录操作日志
     * @param operLog
     */
    void recordOperLog(OperLog operLog);

    /**
     * 分页查询操作日志
     * @param query
     * @return
     */
    PageResult<OperLog> page(OperLogQuery query);

}
