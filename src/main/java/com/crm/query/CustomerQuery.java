package com.crm.query;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.crm.common.model.Query;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: DELL
 * @Date: 2025/10/19
 * @Version: 1.0
 */
@Data
public class CustomerQuery extends Query {
    @Schema(description = "客户名称")
    private String name;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "客户等级")
    private Integer level;
    @Schema(description = "客户来源")
    private Integer source;
    @Schema(description = "跟进状态")
    private Integer followStatus;
    @Schema(description = "0-客户列表，1-公海列表")
    private Integer isPublic;
}
