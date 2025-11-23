package com.crm.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: DELL
 * @Date: 2025/11/16
 * @Version: 1.0
 */
@Data
public class ApprovalQuery {
    @NotNull(message = "审核id不能为空")
    private Integer id;

    @NotNull(message = "审核状态不能为空")
    private Integer type;
}
