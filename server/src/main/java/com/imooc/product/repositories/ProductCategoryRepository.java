package com.imooc.product.repositories;


import com.imooc.product.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    @Query(value = "select * from product_category p where p.category_type = ?1 and p.category_id != ?2", nativeQuery = true)
    ProductCategory selectByCategoryTypeAndId(Integer categoryType, Integer categoryId);

    ProductCategory findByCategoryType(Integer categoryType);

}
