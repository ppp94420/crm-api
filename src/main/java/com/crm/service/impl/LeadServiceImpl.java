package com.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.common.exception.ServerException;
import com.crm.common.result.PageResult;
import com.crm.convert.CustomerConvert;
import com.crm.entity.Customer;
import com.crm.entity.FollowUp;
import com.crm.entity.Lead;
import com.crm.mapper.CustomerMapper;
import com.crm.mapper.FollowUpMapper;
import com.crm.mapper.LeadMapper;
import com.crm.query.IdQuery;
import com.crm.query.LeadQuery;
import com.crm.security.user.SecurityUser;
import com.crm.service.LeadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crm
 * @since 2025-10-12
 */
@Service
public class LeadServiceImpl extends ServiceImpl<LeadMapper, Lead> implements LeadService {

    private final FollowUpMapper followUpMapper;
    @Autowired
    private CustomerMapper customerMapper;

    public LeadServiceImpl(FollowUpMapper followUpMapper) {
        this.followUpMapper = followUpMapper;
    }

    @Override
    public PageResult<Lead> getPage(LeadQuery query) {
        // 构建分页对象
        Page<Lead> page = new Page<>(query.getPage(), query.getLimit());

        // 构建查询条件
        LambdaQueryWrapper<Lead> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(query.getName())) {
            wrapper.like(Lead::getName, query.getName());
        }
        if (query.getFollowStatus() != null) {
            wrapper.eq(Lead::getFollowStatus, query.getFollowStatus());
        }
        if (query.getStatus() != null) {
            wrapper.eq(Lead::getStatus, query.getStatus());
        }
        // 按创建时间倒序排序
        wrapper.orderByDesc(Lead::getCreateTime);

        // 执行分页查询
        Page<Lead> leadPage = baseMapper.selectPage(page, wrapper);

        // 封装返回结果
        return new PageResult<>(leadPage.getRecords(), leadPage.getTotal());
    }

    @Override
    public void saveOrEdit(Lead lead) {
        LambdaQueryWrapper<Lead> wrapper = new LambdaQueryWrapper<Lead>().eq(Lead::getName, lead.getName());
        Customer customer = customerMapper.selectOne(new LambdaQueryWrapper<Customer>().eq(Customer::getPhone, lead.getPhone()));
        if (customer !=null && lead.getStatus() == 1) {
            throw new ServerException("该手机号客户已存在，请勿重复添加线索信息");
        }
        if (lead.getId() == null) {
            Lead selectLead = baseMapper.selectOne(wrapper);
            if (selectLead != null) {
                throw new ServerException("该线索名称已经存在，请勿重复添加线索信息");
            }
            lead.setOwnerId(SecurityUser.getManagerId());
            baseMapper.insert(lead);
        } else {
            wrapper.ne(Lead::getId, lead.getId());
            Lead selectLead = baseMapper.selectOne(wrapper);
            if (selectLead != null) {
                throw new ServerException("该线索名称已经存在，请勿重复添加线索信息");
            }
            baseMapper.updateById(lead);
        }
    }

    @Override
    public void  followLead(FollowUp followUp) {
        Lead lead = baseMapper.selectById(followUp.getId());
        if (lead == null) {
            throw new ServerException("线索不存在，跟进失败");
        }
        lead.setNextFollowStatus(followUp.getNextFollowType());
        baseMapper.updateById(lead);
        followUp.setId(null);
        followUp.setCustomerId(lead.getId());
        followUp.setTargetType(1);
        followUpMapper.insert(followUp);
    }

    @Override
    public void convertToCustomer(IdQuery idQuery){
        Lead lead = baseMapper.selectById(idQuery.getId());
        if (lead == null){
            throw  new ServerException("该线索不存在，客户转化失败");
        }
        Customer customer = CustomerConvert.INSTANCE.leadConvert(lead);
        customer.setId(null);
        customer.setCreaterId(SecurityUser.getManagerId());
        customerMapper.insert(customer);
        lead.setStatus(1);
        baseMapper.updateById(lead);
    }
}
