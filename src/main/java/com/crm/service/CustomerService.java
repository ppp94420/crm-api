package com.crm.service;

import com.crm.common.result.PageResult;
import com.crm.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crm.query.CustomerQuery;
import com.crm.query.CustomerTrendQuery;
import com.crm.query.IdQuery;
import com.crm.vo.CustomerVO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author crm
 * @since 2025-10-12
 */
public interface CustomerService extends IService<Customer> {
   /**
     * 客户列表-分页
     * @param query
     * @return
     */
   PageResult<CustomerVO> getPage(CustomerQuery query);

   /**
     * 导出客户信息
     * @param query
     * @param httpResponse
     */
   void exportCustomer(CustomerQuery query, HttpServletResponse httpResponse);

   /**
     * 新增或修改客户
     * @param customerVO
     */
   void saveOrUpdate(CustomerVO customerVO);

   /**
     * 删除客户信息
     * @param ids
     */
   void removeCustomer(List<Integer> ids);


   /**
     * 客户转入公海
     * @param idQuery
     */
   void customerToPublicPool(IdQuery idQuery);

   /**
     * 领取客户
     * @param idQuery
     */
   void publicPoolToPrivate(IdQuery idQuery);

   /**
    * 客户变化趋势
    * @param query
    * @return
    */
   Map<String,List> getCustomerTrendData(CustomerTrendQuery query);
}
