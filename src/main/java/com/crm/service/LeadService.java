package com.crm.service;

import com.crm.common.result.PageResult;
import com.crm.entity.FollowUp;
import com.crm.entity.Lead;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crm.query.IdQuery;
import com.crm.query.LeadQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author crm
 * @since 2025-10-12
 */
public interface LeadService extends IService<Lead> {
    PageResult<Lead> getPage(LeadQuery query);

    void saveOrEdit(Lead lead);

    /**
     * 跟进线索
     * @param followUp
     */
    void followLead(FollowUp followUp);

    /**
     * 线索转化成客户
     * @param idQuery
     */
    void convertToCustomer(IdQuery idQuery);
}
