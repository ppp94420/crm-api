package com.crm.query;

import com.crm.common.model.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: DELL
 * @Date: 2025/10/12
 * @Version: 1.0
 */
@Data
public class DepartmentQuery extends Query {
    @Schema(description = "部门名称")
    private String name;
}
