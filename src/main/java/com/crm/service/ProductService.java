package com.crm.service;

import com.crm.common.result.PageResult;
import com.crm.entity.Department;
import com.crm.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crm.query.DepartmentQuery;
import com.crm.query.IdQuery;
import com.crm.query.ProductQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author crm
 * @since 2025-10-12
 */
public interface ProductService extends IService<Product> {
        /**
         * 商品列表
         * @param query
         * @return
         */
        PageResult<Product> getPage(ProductQuery query);

        /**
         * 新增或修改商品
         * @param product
         */
        void saveOrEdit(Product product);

        /**
         * 批量修改商品状态
         */
        void batchUpdateProductState();
}
