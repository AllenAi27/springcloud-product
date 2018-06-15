package com.imooc.product.service;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    List<ProductInfoOutput> findByProductIdList(List<String> productIdList);

//    //加库存
//    void increaseStock(List<CartDto> cartDtoList);
//
    //减库存
    void decreaseStock(List<DecreaseStockInput> cartDtoList);

//    //上架
//    ProductInfo onSaleProduct(String productId);
//
//    //下架
//    ProductInfo offSaleProduct(String productId);
}
