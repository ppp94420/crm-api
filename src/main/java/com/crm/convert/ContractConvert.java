package com.crm.convert;

import com.crm.entity.Contract;
import com.crm.entity.ContractProduct;
import com.crm.vo.ContractVO;
import com.crm.vo.ProductVO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: DELL
 * @Date: 2025/11/2
 * @Version: 1.0
 */
@Mapper
public interface ContractConvert {
    ContractConvert INSTANCE = Mappers.getMapper(ContractConvert.class);

    Contract toConvert(ContractVO contractVO);

    ProductVO toProductVO(ContractProduct product);

    List<ProductVO> toProductVOList(List<ContractProduct> ProductList);
}
