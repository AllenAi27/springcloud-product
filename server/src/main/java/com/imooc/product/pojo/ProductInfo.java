package com.imooc.product.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@NoArgsConstructor
public class ProductInfo {

    @Id
    private String productId;

    /** 商品名称 **/
    private String productName;

    /** 商品单价 **/
    private BigDecimal productPrice;

    /** 商品库存 **/
    private Integer productStock;

    /** 商品描述 **/
    private String productDescription;

    /** 商品小图 **/
    private String productIcon;

    /** 商品品类 **/
    private Integer categoryType;

    /** 商品状态 0正常 1下架 **/
    private Integer productStatus;

    private Date createTime;

    private Date updateTime;
}
