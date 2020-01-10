package com.sunup.noend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 1:51 2019/12/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutboundOrderParam  {
    private String commodityNames;
    private String commodityIds;
    private String norms;
    private String lotNumbers;
    private String amounts;
    private String unitPrices;


}
