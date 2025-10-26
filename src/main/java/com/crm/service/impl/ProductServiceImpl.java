package com.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.common.exception.ServerException;
import com.crm.common.result.PageResult;
import com.crm.entity.Product;
import com.crm.mapper.ProductMapper;
import com.crm.query.ProductQuery;
import com.crm.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crm
 * @since 2025-10-12
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Override
    public PageResult<Product> getPage(ProductQuery query) {
        // 1、声明分页参数
        Page<Product> page = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        // 2、添加查询条件
        if (StringUtils.isNotBlank(query.getName())) {
            wrapper.like(Product::getName, query.getName());
        }
        if (query.getStatus() != null) {
            wrapper.eq(Product::getStatus, query.getStatus());
        }
        wrapper.orderByDesc(Product::getCreateTime);
        // 3、查询商品分页列表
        Page<Product> result = baseMapper.selectPage(page, wrapper);
        return new PageResult<>(result.getRecords(), page.getTotal());
    }

    @Override
    public void saveOrEdit(Product product) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<Product>().eq(Product::getName, product.getName());
        if (product.getId() == null) {
            Product newProduct = baseMapper.selectOne(wrapper);
            if (newProduct != null) {
                throw new ServerException("商品名称已经存在,请勿重复添加");
            }
            baseMapper.insert(product);
        } else {
            wrapper.ne(Product::getId, product.getId());
            Product oldProduct = baseMapper.selectOne(wrapper);
            if (oldProduct != null) {
                throw new ServerException("商品名称已经存在,请勿重复添加");
            }
            baseMapper.updateById(product);
        }
    }

    @Override
    public void batchUpdateProductState() {
//      定时下架时间早于当前定时任务执行的时间，修改商品状态
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<Product>()
                .lt(Product::getOffShelfTime, LocalDateTime.now());
        Product offProduct = new Product();
        offProduct.setStatus(2);
        offProduct.setOffShelfTime(null);
        baseMapper.update(offProduct, wrapper);

        wrapper.clear();
        wrapper.lt(Product::getOnShelfTime, LocalDateTime.now());
        Product onProduct = new Product();
        onProduct.setStatus(1);
        onProduct.setOnShelfTime(null);
        baseMapper.update(onProduct, wrapper);
    }
}