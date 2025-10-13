package com.crm.query;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: DELL
 * @Date: 2025/10/12
 * @Version: 1.0
 */
@Data
public class IdQuery {

    @NotNull(message = "id不能为空")
    private Integer id;
}
