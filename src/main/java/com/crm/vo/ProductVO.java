package com.crm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: DELL
 * @Date: 2025/11/2
 * @Version: 1.0
 */
@Data
public class ProductVO {
    @JsonProperty("Id")
    private Integer Id;
    @JsonProperty("pId")
    private Integer pId;
    @JsonProperty("pName")
    private String pName;
    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;
    private Integer count;
    private BigDecimal price;

    public Integer getId() {
        if (this.Id == null) {
            return this.pId;
        }
        return this.Id;
    }
}
