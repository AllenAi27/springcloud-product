package com.imooc.product.service;

import com.imooc.product.pojo.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

//    ProductCategory save(ProductCategory productCategory);
//
    Page<ProductCategory> findAll(Pageable pageable);

//    ProductCategory save(ProductCategory productCategory, ProductCategoryForm form);

//    void delete(Integer categoryId);
}
