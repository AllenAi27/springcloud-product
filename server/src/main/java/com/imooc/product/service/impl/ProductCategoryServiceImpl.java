package com.imooc.product.service.impl;


import com.imooc.product.pojo.ProductCategory;
import com.imooc.product.repositories.ProductCategoryRepository;
import com.imooc.product.repositories.ProductInfoRepository;
import com.imooc.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryRepository.getOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

//    @Override
//    public ProductCategory save(ProductCategory productCategory) {
//        if(!checkProductCategory(productCategory)){
//            throw new SellException(ResultEnum.PRODUCT_CATEGORY_EXIST);
//        }
//        return productCategoryRepository.save(productCategory);
//    }

    @Override
    public Page<ProductCategory> findAll(Pageable pageable) {
        return productCategoryRepository.findAll(pageable);
    }

//    @Override
//    public ProductCategory save(ProductCategory productCategory, ProductCategoryForm form) {
//        ProductCategory checkCategory = new ProductCategory();
//        BeanUtils.copyProperties(form,checkCategory);
//        if(!checkProductCategory(checkCategory)){
//            throw new SellException(ResultEnum.PRODUCT_CATEGORY_EXIST);
//        }
//        List<ProductInfo> productInfoList = productInfoRepository.findByCategoryType(productCategory.getCategoryType());
//        if(!CollectionUtils.isEmpty(productInfoList)){
//            for(ProductInfo productInfo : productInfoList){
//                productInfo.setCategoryType(form.getCategoryType());
//                productInfoRepository.save(productInfo);
//            }
//        }
//        BeanUtils.copyProperties(form,productCategory);
//        return productCategoryRepository.save(productCategory);
//    }
//
//    @Override
//    public void delete(Integer categoryId) {
//         ProductCategory productCategory = productCategoryRepository.findOne(categoryId);
//         if(productCategory == null){
//             throw new SellException(ResultEnum.PRODUCT_CATEGORY_NOT_EXIST);
//         }
//         List<ProductInfo> productInfoList = productInfoRepository.findByCategoryType(productCategory.getCategoryType());
//         if(!CollectionUtils.isEmpty(productInfoList)){
//             throw new SellException(ResultEnum.PRODUCT_CATEGORY_CAN_NOT_DELETE);
//         }
//         productCategoryRepository.delete(categoryId);
//    }

    private Boolean checkProductCategory(ProductCategory productCategory){
        if(productCategory.getCategoryId() == null){
            if(productCategoryRepository.findByCategoryType(productCategory.getCategoryType()) == null){
                return true;
            }else{
                return false;
            }
        }
        ProductCategory result = productCategoryRepository.selectByCategoryTypeAndId(productCategory.getCategoryType(),productCategory.getCategoryId());
        if(result == null){
            return true;
        }else{
            return false;
        }
    }
}
