package com.crm.query;

import com.crm.common.model.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: DELL
 * @Date: 2025/11/9
 * @Version: 1.0
 */

@Data
public class OperLogQuery extends Query {
    @ApiModelProperty("操作人账号")
    private String operName;

    @ApiModelProperty("业务日志操作时间段")
    private List<Timestamp> operTime;

    @ApiModelProperty("接口url(精确)")
    private String operUrl;
}
