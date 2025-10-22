package com.crm.convert;

import com.crm.entity.Customer;
import com.crm.vo.CustomerVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: DELL
 * @Date: 2025/10/19
 * @Version: 1.0
 */
@Mapper
public interface CustomerConvert {
    CustomerConvert INSTANCE = Mappers.getMapper(CustomerConvert.class);

    Customer convert(CustomerVO customerVO);
}
